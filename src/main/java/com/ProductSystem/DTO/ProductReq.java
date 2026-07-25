package com.ProductSystem.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
//                  user requesting into system
public class ProductReq {

    @NotEmpty
    private String productName;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    private Long categoryId;
}
