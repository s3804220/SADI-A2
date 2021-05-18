package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.exception.ProductNotFoundException;
import com.example.BackendComponent.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return StreamSupport
                .stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
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

    public Product updateProduct(Long id, Product newProduct){
        Product productToEdit = getProductById(id);
        productToEdit.setProductName(newProduct.getProductName());
        productToEdit.setBrand(newProduct.getBrand());
        productToEdit.setPrice(newProduct.getPrice());
        productToEdit.setCompany(newProduct.getCompany());
        productToEdit.setDescription(newProduct.getDescription());
        productToEdit.setModel(newProduct.getModel());
        return productToEdit;
    }
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}
//
//    public List<Product> getAllProducts(){
//        return this.sessionFactory.getCurrentSession().createQuery("from Product").list();
//    }
//
//    public Long addProduct(Product product){
//        productRepository.save(product);
//        return product.getProductID();
//    }
}
