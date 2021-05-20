package com.example.BackendComponent.controller;

import java.util.List;

import com.example.BackendComponent.entity.Category;
import com.example.BackendComponent.service.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;
    private final UnifiedService unifiedService;

    @Autowired
    public ProductController(ProductService productService, UnifiedService unifiedService, UnifiedService unifiedService1) {
        this.productService = productService;
        this.unifiedService = unifiedService1;
    }

    @GetMapping(path="/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts(null);
    }

    @GetMapping(path="/products/{id}")
    public Product getProductById(@PathVariable final Long id){
        return productService.getProductById(id);
    }

    @PostMapping(path="/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping(path="/products/{id}")
    public Product deleteProduct(@PathVariable final Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping(path="/products/{id}")
    public Product updateProduct(@PathVariable final Long id, @RequestBody final Product product){
        return productService.updateProduct(id, product);
    }

    @PostMapping(path="/products/{productID}/category/{categoryID}")
    public Category addProductToCategory(@PathVariable final Long productID, @PathVariable final Long categoryID){
        return unifiedService.addProductToCategory(categoryID, productID);
    }

    @DeleteMapping(path="/products/{productID}/category/{categoryID}")
    public Category deleteProductFromCategory(@PathVariable final Long productID, @PathVariable final Long categoryID){
        return unifiedService.removeProductFromCategory(categoryID, productID);
    }

    @RequestMapping(path="/products/search")
    public List<Product> searchProduct(@Param("keyword") String keyword){
        return productService.getAllProducts(keyword);
    }
}
