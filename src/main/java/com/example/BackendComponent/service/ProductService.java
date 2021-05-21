package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.exception.ProductAlreadyExistException;
import com.example.BackendComponent.exception.ProductNotFoundException;
import com.example.BackendComponent.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /*@Autowired
    public ProductService(ProductRepository productRepository, OrderService orderService) {
        this.productRepository = productRepository;
    }*/

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

    /*public List<Product> getAllProducts(){
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }*/
    public List<Product> getAllProducts(String keyword){
        if (keyword != null){
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
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
