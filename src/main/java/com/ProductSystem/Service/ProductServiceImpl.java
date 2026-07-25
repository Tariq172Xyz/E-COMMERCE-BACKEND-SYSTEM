package com.ProductSystem.Service;

import com.ProductSystem.DTO.ProductReq;
import com.ProductSystem.DTO.ProductResp;
import com.ProductSystem.DTO.ProductUpdateReq;
import com.ProductSystem.Entity.Category;
import com.ProductSystem.Entity.Product;
import com.ProductSystem.Exceptions.CategoryNotFoundException;
import com.ProductSystem.Exceptions.ProductAlreadyExistsException;
import com.ProductSystem.Exceptions.ProductNotFoundException;
import com.ProductSystem.Repository.CategoryRepository;
import com.ProductSystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
   private final ModelMapper modelMapper;
   private final CategoryRepository categoryRepository;

    @Override
    public ProductResp addProduct(ProductReq productReq) {

        if (productRepository.existsByproductName(productReq.getProductName())) {
            throw new ProductAlreadyExistsException("Product with this name already exists");
        }

        Category category = categoryRepository.findById(productReq.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        // ✅ Build manually instead of modelMapper
        Product newProduct = new Product();
        newProduct.setProductName(productReq.getProductName());
        newProduct.setPrice(productReq.getPrice());
        newProduct.setQuantity(productReq.getQuantity());
        newProduct.setCategory(category);

        Product savedProduct = productRepository.save(newProduct);

        return mapToResp(savedProduct);
    }

    @Override
    public ProductResp getProductById(Long productId) {

        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product with this ID not found"));

        return mapToResp(product);
    }

    @Override
    public void deleteProduct(Long productId) {

      Product product=productRepository.findById(productId)
              .orElseThrow(()-> new ProductNotFoundException("Product not found"));

      productRepository.delete(product);


    }

    @Override
    public ProductResp updateProduct(Long productId, ProductUpdateReq productUpdateReq) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // only update if NOT null
        if (productUpdateReq.getProductName() != null) {
            product.setProductName(productUpdateReq.getProductName());
        }
        if (productUpdateReq.getPrice() != null) {
            product.setPrice(productUpdateReq.getPrice());
        }
        if (productUpdateReq.getQuantity() != null) {
            product.setQuantity(productUpdateReq.getQuantity());
        }
        Product updatedProduct = productRepository.save(product);
        return mapToResp(updatedProduct);
    }

    @Override
    public List<ProductResp> getAllProducts() {
        List<Product>allProducts=productRepository.findAll();

        return allProducts.stream()
                .map(product->mapToResp(product)).collect(Collectors.toList());
    }


    //to handle the nested fields in the class
    //categoryId and name are nested -> cannot be directly
    // accessed that why we manage them manually
    private ProductResp mapToResp(Product product){
        ProductResp resp=modelMapper.map(product,ProductResp.class);
        resp.setCategoryId(product.getCategory().getCategoryId());
        resp.setCategoryName(product.getCategory().getCategoryName());

        return resp;
    }
}
