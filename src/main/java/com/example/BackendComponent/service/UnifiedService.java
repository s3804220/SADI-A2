package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Transactional
@Service
public class UnifiedService {
    private final ProductService productService;
    private final OrderService orderService;
    private final StaffService staffService;
    private final ProviderService providerService;
    private final CategoryService categoryService;

    public UnifiedService(ProductService productService, OrderService orderService, StaffService staffService, ProviderService providerService, CategoryService categoryService) {
        this.staffService = staffService;
        this.productService = productService;
        this.orderService = orderService;
        this.providerService = providerService;
        this.categoryService = categoryService;
    }

    public Product addProductToOrder(Long productID, Long orderId){
        Product product = productService.getProductById(productID);
        Order order = orderService.getOrderByID(orderId);
        product.addProductOrder(order);
        order.setOrderProduct(product);
        return product;
    }

    public Product removeProductFromOrder(Long productID, Long orderID){
        Product product = productService.getProductById(productID);
        Order order = orderService.getOrderByID(orderID);
        product.deleteProductOrder(order);
        order.deleteOrderProduct();
        return product;
    }

    public Staff addStaffToOrder(Long staffID, Long orderID){
        Staff staff = staffService.getStaffByID(staffID);
        Order order = orderService.getOrderByID(orderID);
        staff.addStaffOrders(order);
        order.setOrderStaff(staff);
        return staff;
    }

    public Staff removeStaffFromOrder(Long staffID, Long orderID){
        Staff staff = staffService.getStaffByID(staffID);
        Order order = orderService.getOrderByID(orderID);
        staff.deleteStaffOrders(order);
        order.deleteOrderStaff();
        return staff;
    }

    public Provider addProviderToOrder(Long providerID, Long orderID){
        Provider provider = providerService.getProviderByID(providerID);
        Order order = orderService.getOrderByID(orderID);
        provider.addProviderOrder(order);
        order.setOrderProvider(provider);
        return provider;
    }

    public Provider removeProviderFromOrder(Long providerID, Long orderID){
        Provider provider = providerService.getProviderByID(providerID);
        Order order = orderService.getOrderByID(orderID);
        provider.deleteProviderOrder(order);
        order.deleteOrderProvider();
        return provider;
    }

    public Category addProductToCategory(Long categoryID, Long productID){
        Category category = categoryService.getCategoryById(categoryID);
        Product product = productService.getProductById(productID);
        category.addProduct(product);
        product.setCategory(category);
        return category;
    }

    public Category removeProductFromCategory(Long categoryID, Long productID){
        Category category = categoryService.getCategoryById(categoryID);
        Product product = productService.getProductById(productID);
        category.removeProduct(product);
        product.deleteCategory();
        return category;
    }
}
