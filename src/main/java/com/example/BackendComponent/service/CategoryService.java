package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.CategoryNotFoundException;
import com.example.BackendComponent.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.BackendComponent.entity.*;

@Transactional
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return StreamSupport
                .stream(categoryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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

    public Category updateCategory(Long id, Category category){
        Category categoryToUpdate = getCategoryById(id);
        categoryToUpdate.setCategoryName(category.getCategoryName());
        return categoryToUpdate;
    }

    public Category addProductToCategory(Long categoryID, Long productID){
        Category category = getCategoryById(categoryID);
        Product product = productService.getProductById(productID);
        category.addProduct(product);
        product.setCategory(category);
        return category;
    }

    public Category removeProductFromCategory(Long categoryID, Long productID){
        Category category = getCategoryById(categoryID);
        Product product = productService.getProductById(productID);
        category.removeProduct(product);
        product.deleteCategory();
        return category;
    }
//    @Autowired
//    private SessionFactory sessionFactory;
//    public void setSessionFactory(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}
//
//    public List<Category> getAllCategories(){
//        return this.sessionFactory.getCurrentSession().createQuery("from Category").list();
//    }
//
//    public Category addCategory(Category category){
//        sessionFactory.getCurrentSession().save(category);
//        return category;
//    }
//
//    public List<Category> findCategoryById(Long ID){
//        return this.sessionFactory.getCurrentSession().createQuery("from Category c where c.categoryID = " + ID).list();
//    }
}
