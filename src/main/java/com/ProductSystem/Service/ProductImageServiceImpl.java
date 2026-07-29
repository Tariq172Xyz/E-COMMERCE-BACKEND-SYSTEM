package com.ProductSystem.Service;

import com.ProductSystem.Configuration.FileStorageProperties;
import com.ProductSystem.DTO.ProductImageResp;
import com.ProductSystem.Entity.Product;
import com.ProductSystem.Entity.ProductImage;
import com.ProductSystem.Exceptions.FileInvalidException;
import com.ProductSystem.Exceptions.FileStorageException;
import com.ProductSystem.Exceptions.ProductNotFoundException;
import com.ProductSystem.Repository.ProductImageRepository;
import com.ProductSystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


@Transactional
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "file")
public class ProductImageServiceImpl implements productImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final FileStorageProperties fileStorageProperties;


    @Override
    public ProductImageResp uploadImage(Long productId, MultipartFile file){

        Product product=productRepository.findById(productId).
                        orElseThrow(()->new ProductNotFoundException("product not found"));

        if (file==null ||file.isEmpty()){
            throw new FileInvalidException("image file cannot be empty");
        }
        if (!file.getContentType().startsWith("image/")){
            throw new FileInvalidException("only images are allowed");
        }

        if (file.getSize()>fileStorageProperties.getMaxSize().toBytes()){
            throw new FileInvalidException("file size cannot exceed 5MB");
        }

        String originalFileName= file.getOriginalFilename();
        String extension="";
        int lastDotIndex=originalFileName.lastIndexOf('.');

        if (lastDotIndex!=-1){
            extension=originalFileName.substring(lastDotIndex);
        }

        String storedFileName= UUID.randomUUID().toString()+extension;
        Path uploadPath = Paths.get(fileStorageProperties.getUploadDirectory());
        Path targetPath=uploadPath.resolve(storedFileName);

        try{
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
        Files.copy(file.getInputStream(),targetPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ex){
            throw new FileStorageException("Failed to store image",ex);
        }

        ProductImage productImage=new ProductImage();
        productImage.setContentType(file.getContentType());
        productImage.setSize(file.getSize());
        productImage.setOriginalFileName(originalFileName);
        productImage.setStoredFileName(storedFileName);
        productImage.setProduct(product);

        try {
            ProductImage savedImage=productImageRepository.save(productImage);
            return modelMapper.map(savedImage, ProductImageResp.class);
        }catch (Exception ex){
            try{
                Files.delete(targetPath);
            }catch (IOException e){
            }
            throw ex;
        }
    }

    @Override
    public Resource loadImage(Long imageId) {
        return null;
    }

    @Override
    public void deleteImage(Long imageId) {

    }

    @Override
    public List<ProductImage> getImagesByProduct(Long productId) {
        return List.of();
    }
}
