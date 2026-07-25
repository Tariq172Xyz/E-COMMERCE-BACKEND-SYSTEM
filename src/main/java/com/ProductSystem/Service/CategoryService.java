package com.ProductSystem.Service;

import com.ProductSystem.DTO.CategoryReq;
import com.ProductSystem.DTO.CategoryResp;
import java.util.List;

public interface CategoryService {

    CategoryResp createCategory(CategoryReq categoryReq);

    List<CategoryResp>getAllCategories();

    void deleteCategory(Long categoryId);

    CategoryResp getCategoryById(Long categoryId);

    CategoryResp updateCategory(Long categoryId, CategoryReq categoryReq);
}
