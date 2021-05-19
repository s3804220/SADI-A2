package com.example.BackendComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.entity.Category;
import com.example.BackendComponent.service.CategoryService;
import com.example.BackendComponent.repository.CategoryRepository;

@RestController
@RequestMapping(path="/api")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path="/categories")
    public Category addCategory(@RequestBody final Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping(path="/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping(path="/categories/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping(path="/categories/{id}")
    public Category deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping(path="/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.updateCategory(id, category);
    }
}
