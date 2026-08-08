package com.ProductSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageListItemResp {
    private Long imageId;
    private String originalFileName;
    private String imageUrl;
}
