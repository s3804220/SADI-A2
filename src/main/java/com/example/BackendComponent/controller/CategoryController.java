package com.example.BackendComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.entity.Category;
import com.example.BackendComponent.service.CategoryService;
import com.example.BackendComponent.repository.CategoryRepository;

@RestController
@RequestMapping(path="/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /*@Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }*/

    @PostMapping(path="/categories")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping(path="/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories(null);
    }

    @GetMapping(path="/categories/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping(path="/categories/{id}")
    public Category deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }

    @PutMapping(path="/categories")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @RequestMapping(path="/categories/search")
    public List<Category> searchCategory(@Param("keyword") String keyword){
        return categoryService.getAllCategories(keyword);
    }
}
