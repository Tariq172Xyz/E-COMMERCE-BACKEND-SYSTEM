package com.ProductSystem.Service;

import com.ProductSystem.DTO.ProductReq;
import com.ProductSystem.DTO.ProductResp;
import com.ProductSystem.DTO.ProductUpdateReq;

import java.util.List;

public interface ProductService {

    ProductResp addProduct(ProductReq productReq);
    ProductResp getProductById(Long productId);
    void deleteProduct(Long productId);
    ProductResp updateProduct(Long productId, ProductUpdateReq productUpdateReq);
    List<ProductResp>getAllProducts();

}
