package com.example.BackendComponent.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.BigDecimalType;
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

    @GetMapping(path="/products")
    public List<Product> getAllProducts(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return productService.getAllProducts(thePage,pageable);
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

    @GetMapping(path="/products/search")
    public List<Product> searchProduct(@RequestParam("keyword") String keyword, @RequestParam Optional<Integer> page) {
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return productService.searchProduct(keyword,thePage,pageable);
    }

    @GetMapping(path="/products/search/filter")
    public List<Product> searchProductBy(@RequestParam Optional<String> name, @RequestParam Optional<String> model, @RequestParam Optional<String> brand, @RequestParam Optional<String> company, @RequestParam Optional<String> description, @RequestParam Optional<BigDecimal> minprice, @RequestParam Optional<BigDecimal> maxprice, @RequestParam Optional<Integer> page){
        String newname, newmodel, newbrand, newcompany, newdescript;
        BigDecimal newmin, newmax;
        newname = name.orElse(null);
        newmodel = model.orElse(null);
        newbrand = brand.orElse(null);
        newcompany = company.orElse(null);
        newdescript = description.orElse(null);
        newmin = minprice.orElse(null);
        newmax = maxprice.orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return productService.searchProductBy(newname,newmodel,newbrand,newcompany,newdescript,newmin,newmax,thePage,pageable);
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
