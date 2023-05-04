package com.sharif.blog.services.impl;

import com.sharif.blog.entities.Category;
import com.sharif.blog.exceptions.ResourceNotFoundException;
import com.sharif.blog.payloads.CategoryDto;
import com.sharif.blog.repository.CategoryRepo;
import com.sharif.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category save = categoryRepo.save(category);
        return this.categoryToDto(save);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with this ID", "doesntExist", id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save = categoryRepo.save(category);
        return categoryToDto(save);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found", " with categoryId ", id));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> all = categoryRepo.findAll();
        List<CategoryDto> collect = all.stream().map((category -> this.categoryToDto(category))).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found", " with categoryId ", id));
        this.categoryRepo.delete(category);
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }

    private CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }
}
