package com.ProductSystem.Controller;

import com.ProductSystem.DTO.ProductReq;
import com.ProductSystem.DTO.ProductResp;
import com.ProductSystem.DTO.ProductUpdateReq;
import com.ProductSystem.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductResp>addProduct(@Valid @RequestBody ProductReq productReq){
        return ResponseEntity.status(201).body(productService.addProduct(productReq));
    }

    @GetMapping
    public ResponseEntity<List<ProductResp>>getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResp>getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/deleteProduct/{productId}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted Successfully");
    }

    @PatchMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductResp>updateProduct(@PathVariable Long productId,@RequestBody ProductUpdateReq productUpdateReq){
        return ResponseEntity.ok(productService.updateProduct(productId,productUpdateReq));
    }







}
