package com.ProductSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResp {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private BigDecimal quantity;
    private Long categoryId;
    private String categoryName;
}
