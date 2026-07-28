package com.ProductSystem.Controller;

import com.ProductSystem.DTO.ProductImageResp;
import com.ProductSystem.Service.ProductImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductImageController {

      private final ProductImageServiceImpl productImageService;

    @PostMapping("/{productId}/images")
    public ResponseEntity<ProductImageResp>uploadImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file){
        return ResponseEntity.status(201).body(productImageService.uploadImage(productId,file));



    }
}
