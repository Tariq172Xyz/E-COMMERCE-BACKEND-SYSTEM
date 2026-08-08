package com.ProductSystem.Service;

import com.ProductSystem.DTO.ProductImageListItemResp;
import com.ProductSystem.DTO.ProductImageResp;
import com.ProductSystem.Entity.LoadedImage;
import com.ProductSystem.Entity.ProductImage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface productImageService {
    ProductImageResp uploadImage(Long productId, MultipartFile file);
    LoadedImage loadImage(Long imageId);
    void deleteImage(Long imageId);
    List<ProductImageListItemResp> getImagesByProduct(Long productId);
}
