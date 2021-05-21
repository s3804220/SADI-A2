package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.CategoryAlreadyExistException;
import com.example.BackendComponent.exception.CategoryNotFoundException;
import com.example.BackendComponent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.BackendComponent.entity.*;

@Transactional
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    //private final ProductService productService;

    /*@Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }*/

    public Category addCategory(Category category){
        if(!categoryRepository.existsById(category.getCategoryID())){
            categoryRepository.save(category);
        } else {
            throw new CategoryAlreadyExistException(category.getCategoryID());
        }
        return category;
    }

    public List<Category> getAllCategories(String keyword) {
        if (keyword != null) {
            return categoryRepository.search(keyword);
        }
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(id));
    }

    public Category deleteCategory(Long id){
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
        return category;
    }

    public Category updateCategory(Category category){
        if(categoryRepository.existsById(category.getCategoryID())){
            categoryRepository.save(category);
        } else {
            throw new CategoryNotFoundException(category.getCategoryID());
        }
        return category;
    }
}
