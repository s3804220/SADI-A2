package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
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
    private final OrderService orderService;

    @Autowired
    public ProductService(ProductRepository productRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.orderService = orderService;
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

    public Product addProductToOrder(Long productID, Long orderId){
        Product product = getProductById(productID);
        Order order = orderService.getOrderByID(orderId);
        product.addProductOrder(order);
        order.setOrderProduct(product);
        return product;
    }

    public Product removeProductFromOrder(Long productID, Long orderID){
        Product product = getProductById(productID);
        Order order = orderService.getOrderByID(orderID);
        product.deleteProductOrder(order);
        order.deleteOrderProduct();
        return product;
    }
}
