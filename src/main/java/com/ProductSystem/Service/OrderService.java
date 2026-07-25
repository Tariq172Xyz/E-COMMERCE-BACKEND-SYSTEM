package com.ProductSystem.Service;

import com.ProductSystem.DTO.OrderReq;
import com.ProductSystem.DTO.OrderResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderResp placeOrder(OrderReq orderReq);
    OrderResp getOrderById(Long orderId);
    Page<OrderResp> getAllOrders(Pageable pageable);
    List<OrderResp>getOrdersByUser(Long userId);
    OrderResp cancelOrder(Long orderId);
}
