package com.ProductSystem.Service;

import com.ProductSystem.DTO.*;
import com.ProductSystem.Entity.*;
import com.ProductSystem.Exceptions.*;
import com.ProductSystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public OrderResp placeOrder(OrderReq orderReq) {

        User user = userRepository.findById(orderReq.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Address address = addressRepository.findById(orderReq.getAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Address not found"));

        Order order = new Order();

        order.setUser(user);
        order.setDeliveryAddress(address);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.ZERO);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItemReq itemReq : orderReq.getOrderItems()) {
            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product with this ID not found"));

            if (product.getQuantity() < itemReq.getQuantity()) {
                throw new InsufficientStockException("Not enough Stock available for " + product.getProductName());
            }

            //price of single item
            BigDecimal unitPrice = product.getPrice();
            //price of product (ex: 2 nike shoes -> price of 2 shoes )
            BigDecimal subTotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.getQuantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setUnitPrice(unitPrice);
            orderItem.setSubTotal(subTotal);
            product.setQuantity(product.getQuantity() - itemReq.getQuantity());

            productRepository.save(product);
            orderItems.add(orderItem);

            totalAmount = totalAmount.add(subTotal);

        }
        order.setTotalAmount(totalAmount);
        order.setOrderItem(orderItems);

        Order savedOrder = orderRepository.save(order);

        return mapToOrderResp(savedOrder);

    }

    @Override
    public OrderResp getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with this ID not found"));

        return mapToOrderResp(order);
    }

    @Override
    public Page<OrderResp> getAllOrders(Pageable pageable) {
        Page<Order>page= orderRepository.findAll(pageable);
        return page.map(this::mapToOrderResp);
    }


    @Override
    public List<OrderResp> getOrdersByUser(Long userId) {
        if (!userRepository.existsById(userId)){
           throw new UserNotFoundException("User not found");
        }
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToOrderResp).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResp cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException("Order with this ID not found "));

        if (!order.getStatus().equals(Order.OrderStatus.PENDING)){
            throw new InvalidInputException("Only pending orders can be cancelled");
        }

        for (OrderItem item : order.getOrderItem()){
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity()+item.getQuantity());
            productRepository.save(product);
        }
        order.setStatus(Order.OrderStatus.CANCELLED);
        Order cancelledOrder=orderRepository.save(order);

        return mapToOrderResp(cancelledOrder);

    }


    private OrderResp mapToOrderResp(Order order) {

        OrderResp resp = new OrderResp();

        // flat fields — set directly
        resp.setOrderId(order.getOrderId());
        resp.setOrderDate(order.getOrderDate());
        resp.setStatus(order.getStatus());
        resp.setTotalAmount(order.getTotalAmount());

        // nested — get from user object
        resp.setUserId(order.getUser().getUserId());
        resp.setUserName(order.getUser().getUserName());

        // nested — convert address to AddressResp
        resp.setDeliveryAddress(
                modelMapper.map(order.getDeliveryAddress(), AddressResp.class));

        // nested — convert each OrderItem → OrderItemResp
        List<OrderItemResp> itemResps = order.getOrderItem()
                .stream()
                .map(item -> {
                    OrderItemResp itemResp = new OrderItemResp();
                    itemResp.setOrderItemId(item.getOrderItemId());
                    itemResp.setProductId(item.getProduct().getProductId());
                    itemResp.setProductName(item.getProduct().getProductName());
                    itemResp.setQuantity(item.getQuantity());
                    itemResp.setUnitPrice(item.getUnitPrice());
                    itemResp.setSubTotal(item.getSubTotal());
                    return itemResp;
                })
                .collect(Collectors.toList());

        resp.setOrderItems(itemResps);
        return resp;
    }
}
