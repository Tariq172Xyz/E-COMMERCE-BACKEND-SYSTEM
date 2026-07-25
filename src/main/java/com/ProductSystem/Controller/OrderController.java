package com.ProductSystem.Controller;

import com.ProductSystem.DTO.OrderReq;
import com.ProductSystem.DTO.OrderResp;
import com.ProductSystem.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResp> placeOrder(@Valid @RequestBody OrderReq orderReq) {
        return ResponseEntity.status(201)
                .body(orderService.placeOrder(orderReq));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResp> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                orderService.getOrderById(orderId));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResp>> getAllOrders(
            @PageableDefault(size = 5,sort = "orderDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResp>> getOrdersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                orderService.getOrdersByUser(userId));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResp> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                orderService.cancelOrder(orderId));
    }
}