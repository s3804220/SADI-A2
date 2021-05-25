package com.example.BackendComponent.controller;

import java.util.List;
import java.util.Optional;

import com.example.BackendComponent.entity.Customer;
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

    @PostMapping(path="/categories")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping(path="/categories")
    public List<Category> getAllCategories(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return categoryService.getAllCategories(thePage,pageable);
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

    @GetMapping(path="/categories/search")
    public List<Category> searchCategory(@RequestParam("keyword") String keyword, @RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return categoryService.searchCategory(keyword,thePage,pageable);
    }

    @GetMapping(path="/categories/search/filter")
    public List<Category> searchCategoryBy(@RequestParam Optional<String> name, @RequestParam Optional<Integer> page){
        String newname;
        newname = name.orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return categoryService.searchCategoryBy(newname,thePage,pageable);
    }
}
