package com.ProductSystem.DTO;

import com.ProductSystem.Entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResp {
    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private List<OrderItemResp> items;
    private Long userId;
    private String userName;
    private Order.OrderStatus status;
    private AddressResp deliveryAddress;
    private List<OrderItemResp> orderItems;
}
