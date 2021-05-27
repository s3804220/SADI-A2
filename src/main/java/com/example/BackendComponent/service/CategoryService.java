package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.CategoryAlreadyExistException;
import com.example.BackendComponent.exception.CategoryNotFoundException;
import com.example.BackendComponent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class
CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        if(!categoryRepository.existsById(category.getCategoryID())){
            categoryRepository.save(category);
        } else {
            throw new CategoryAlreadyExistException(category.getCategoryID());
        }
        return category;
    }

    public List<Category> getAllCategories(int page, boolean pageBool) {
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return categoryRepository.findAll(pageable).getContent();
    }

    public List<Category> searchCategory(String keyword,int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return categoryRepository.search(keyword,pageable).getContent();
    }

    public List<Category> searchCategoryBy(String name, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return categoryRepository.searchCategoryBy(name, pageable).getContent();
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
