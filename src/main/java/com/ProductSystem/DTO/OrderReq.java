package com.ProductSystem.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {
    @NotNull
    @NotEmpty
    private List<OrderItemReq> orderItems;  // ✅ the items they want to order

    @NotNull
    private Long addressId;
    @NotNull
    private Long userId;

}
