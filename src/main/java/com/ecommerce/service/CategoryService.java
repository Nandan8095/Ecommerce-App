package com.ecommerce.service;


import com.ecommerce.payload.CategoryDto;
import com.ecommerce.payload.CategoryResponse;


public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);


    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    CategoryDto deleteCategory(Long id);

}
