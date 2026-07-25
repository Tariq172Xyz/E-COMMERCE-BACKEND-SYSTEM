package com.ProductSystem.Service;

import com.ProductSystem.DTO.CategoryReq;
import com.ProductSystem.DTO.CategoryResp;
import com.ProductSystem.Entity.Category;
import com.ProductSystem.Exceptions.CategoryAlreadyExistsException;
import com.ProductSystem.Exceptions.CategoryNotFoundException;
import com.ProductSystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryResp createCategory(CategoryReq categoryReq) {

        if (categoryRepository.existsByCategoryName(categoryReq.getCategoryName())){
            throw  new CategoryAlreadyExistsException("Category with this name already exists");
        }
        Category category=modelMapper.map(categoryReq,Category.class);
        Category newCategory=categoryRepository.save(category);

        return modelMapper.map(newCategory,CategoryResp.class);
    }

    @Override
    public List<CategoryResp> getAllCategories() {

        List<Category> allCategories=categoryRepository.findAll();
        return allCategories.stream()
                .map(category-> modelMapper.map(category,CategoryResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category with this name not found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResp getCategoryById(Long categoryId) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException("Category with this name not found"));

        return modelMapper.map(category,CategoryResp.class);
    }

    @Override
    public CategoryResp updateCategory(Long categoryId, CategoryReq categoryReq) {

        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException("Category not found"));

        category.setCategoryName(categoryReq.getCategoryName());
        category.setDescription(categoryReq.getDescription());

        Category updatedCategory = categoryRepository.save(category);


        return modelMapper.map(updatedCategory,CategoryResp.class);
    }
}
