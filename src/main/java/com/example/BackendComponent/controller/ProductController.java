package com.example.BackendComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path="/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
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
}
