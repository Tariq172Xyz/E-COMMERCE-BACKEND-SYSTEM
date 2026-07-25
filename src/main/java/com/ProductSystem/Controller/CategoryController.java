package com.ProductSystem.Controller;

import com.ProductSystem.DTO.CategoryReq;
import com.ProductSystem.DTO.CategoryResp;
import com.ProductSystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

        private final CategoryService categoryService;

        @PostMapping
        public ResponseEntity<CategoryResp> createCategory(
                @Valid @RequestBody CategoryReq categoryReq) {
            return ResponseEntity.status(201)
                    .body(categoryService.createCategory(categoryReq));
        }

        @GetMapping
        public ResponseEntity<List<CategoryResp>> getAllCategories() {
            return ResponseEntity.ok(categoryService.getAllCategories());
        }

        @GetMapping("/{categoryId}")
        public ResponseEntity<CategoryResp> getCategoryById(
                @PathVariable Long categoryId) {
            return ResponseEntity.ok(
                    categoryService.getCategoryById(categoryId));
        }

        @PutMapping("/{categoryId}")
        public ResponseEntity<CategoryResp> updateCategory(
                @PathVariable Long categoryId,
                @Valid @RequestBody CategoryReq categoryReq) {
            return ResponseEntity.ok(
                    categoryService.updateCategory(categoryId, categoryReq));
        }

        @DeleteMapping("/{categoryId}")
        public ResponseEntity<String> deleteCategory(
                @PathVariable Long categoryId) {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        }

}
