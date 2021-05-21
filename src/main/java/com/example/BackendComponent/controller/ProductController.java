package com.example.BackendComponent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.service.ProductService;

@RestController
@RequestMapping(path="/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    //private final UnifiedService unifiedService;

    /*@Autowired
    public ProductController(ProductService productService, UnifiedService unifiedService, UnifiedService unifiedService1) {
        this.productService = productService;
        this.unifiedService = unifiedService1;
    }*/

    @GetMapping(path="/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts(null);
    }

    @GetMapping(path="/products/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping(path="/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping(path="/products/{id}")
    public Product deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping(path="/products")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(path="/products/search")
    public List<Product> searchProduct(@Param("keyword") String keyword) {
        return productService.getAllProducts(keyword);
    }

    /*@PutMapping(path="/products")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }*/

    /*@PostMapping(path="/products/{productID}/category/{categoryID}")
    public Category addProductToCategory(@PathVariable final Long productID, @PathVariable final Long categoryID){
        return unifiedService.addProductToCategory(categoryID, productID);
    }

    @DeleteMapping(path="/products/{productID}/category/{categoryID}")
    public Category deleteProductFromCategory(@PathVariable final Long productID, @PathVariable final Long categoryID){
        return unifiedService.removeProductFromCategory(categoryID, productID);
    }*/
}
