package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.exception.ProductAlreadyExistException;
import com.example.BackendComponent.exception.ProductNotFoundException;
import com.example.BackendComponent.repository.ProductRepository;
import org.hibernate.type.BigDecimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        if(!productRepository.existsById(product.getProductID())){
            productRepository.save(product);
        }else {
            throw new ProductAlreadyExistException(product.getProductID());
        }
        return product;
    }

    public Product updateProduct(Product product){
        if(productRepository.existsById(product.getProductID())){
            productRepository.save(product);
        }else{
            throw new ProductNotFoundException(product.getProductID());
        }
        return product;
    }

    public List<Product> getAllProducts(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> searchProduct(String keyword, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return productRepository.search(keyword,pageable).getContent();
    }

    public List<Product> searchProductBy(String name, String model, String brand, String company, String description, BigDecimal minprice, BigDecimal maxprice, int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return productRepository.searchProductBy(name,model,brand,company,description,minprice,maxprice, pageable).getContent();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(id));
    }

    public Product deleteProduct(Long id){
        Product product = getProductById(id);
        productRepository.delete(product);
        return product;
    }

    /*public Product updateProduct(Long id, Product newProduct){
        Product productToEdit = getProductById(id);
        productToEdit.setProductName(newProduct.getProductName());
        productToEdit.setBrand(newProduct.getBrand());
        productToEdit.setPrice(newProduct.getPrice());
        productToEdit.setCompany(newProduct.getCompany());
        productToEdit.setDescription(newProduct.getDescription());
        productToEdit.setModel(newProduct.getModel());
        return productToEdit;
    }*/
}
