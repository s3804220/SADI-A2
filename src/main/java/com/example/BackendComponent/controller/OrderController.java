package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.entity.Staff;
import com.example.BackendComponent.service.UnifiedService;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;
    private final UnifiedService unifiedService;

    public OrderController(OrderService orderService, UnifiedService unifiedService) {
        this.orderService = orderService;
        this.unifiedService = unifiedService;
    }

    @GetMapping(path="/orders")
    public List<Order> getAllOrder(){
        return orderService.getAllOrders();
    }

    @GetMapping(path="/orders/{id}")
    public Order getOrderByID(@PathVariable Long id){
        return orderService.getOrderByID(id);
    }

    @PostMapping(path="/orders")
    public Order addOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping(path="/orders/{id}")
    public Order deleteOrder(@PathVariable Long id){
        return orderService.deleteOrder(id);
    }

    @PutMapping(path="/orders/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order){
        return orderService.updateOrder(id, order);
    }

    @PostMapping(path="/orders/{orderID}/product/{productID}")
    public Product addProductToOrder(@PathVariable Long orderID, @PathVariable Long productID){
        return unifiedService.addProductToOrder(productID, orderID);
    }

    @DeleteMapping(path="/orders/{orderID}/product/{productID}")
    public Product removeProductFromOrder(@PathVariable Long orderID, @PathVariable Long productID){
        return unifiedService.removeProductFromOrder(productID, orderID);
    }

    @PostMapping(path="/orders/{orderID}/staff/{staffID}")
    public Staff addStaffToOrder(@PathVariable Long orderID, @PathVariable Long staffID){
        return unifiedService.addStaffToOrder(staffID, orderID);
    }

    @DeleteMapping(path="/orders/{orderID}/staff/{staffID}")
    public Staff removeStaffFromOrder(@PathVariable Long orderID, @PathVariable Long staffID){
        return unifiedService.removeStaffFromOrder(staffID, orderID);
    }

    @PostMapping(path="/orders/{orderID}/provider/{providerID}")
    public Provider addProviderToOrder(@PathVariable Long orderID, @PathVariable Long providerID){
        return unifiedService.addProviderToOrder(providerID, orderID);
    }

    @DeleteMapping(path="/orders/{orderID}/provider/{providerID}")
    public Provider deleteProviderFromOrder(@PathVariable Long orderID, @PathVariable Long providerID){
        return unifiedService.removeProviderFromOrder(providerID, orderID);
    }

    @GetMapping(path="/orders/{orderID}/details")
    public String getOrderDetails(@PathVariable Long orderID){
        return orderService.getOrderDetails(orderID);
    }
}
