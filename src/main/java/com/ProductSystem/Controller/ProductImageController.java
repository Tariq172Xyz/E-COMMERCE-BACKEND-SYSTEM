package com.ProductSystem.Controller;

import com.ProductSystem.DTO.ProductImageListItemResp;
import com.ProductSystem.DTO.ProductImageResp;
import com.ProductSystem.Entity.LoadedImage;
import com.ProductSystem.Service.ProductImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductImageController {

      private final ProductImageServiceImpl productImageService;

    @PostMapping("/{productId}/images")
    public ResponseEntity<ProductImageResp>uploadImage(@PathVariable Long productId, @RequestParam("file") MultipartFile file){
        return ResponseEntity.status(201).body(productImageService.uploadImage(productId,file));
    }

    @DeleteMapping("/{imageId}/images")
    public ResponseEntity<ProductImageResp>deleteImage(@PathVariable Long imageId){
        productImageService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<Resource>loadImage(@PathVariable Long imageId){
        LoadedImage loadedImage=productImageService.loadImage(imageId);

        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(loadedImage.getContentType())).
                body(loadedImage.getResource());
    }

    @GetMapping("/{productId}/images")
    public ResponseEntity<List<ProductImageListItemResp>> getImagesByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productImageService.getImagesByProduct(productId));
    }


}
