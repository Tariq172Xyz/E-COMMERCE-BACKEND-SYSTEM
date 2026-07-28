package com.ProductSystem.DTO;

import lombok.Data;

@Data
public class ProductImageResp {

    private Long imageId;
    private String originalFileName;
    private String storedFileName;
    private String contentType;
    private Long size;
    private Long productId;
}
