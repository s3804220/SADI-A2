package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
}
