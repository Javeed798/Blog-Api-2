package com.sharif.blog.services;

import com.sharif.blog.payloads.CategoryDto;
import com.sharif.blog.repository.CategoryRepo;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer id);
}
