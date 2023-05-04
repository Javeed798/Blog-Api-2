package com.sharif.blog.controllers;

import com.sharif.blog.entities.Category;
import com.sharif.blog.payloads.ApiResponse;
import com.sharif.blog.payloads.CategoryDto;
import com.sharif.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUsers(){
        List<CategoryDto> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        CategoryDto categoryById = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryById,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id){
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true,new Date()),HttpStatus.OK);
    }

}
