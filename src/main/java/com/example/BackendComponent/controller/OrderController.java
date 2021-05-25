package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.Provider;
import com.example.BackendComponent.entity.Staff;
//import com.example.BackendComponent.service.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BackendComponent.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*public OrderController(OrderService orderService, UnifiedService unifiedService) {
        this.orderService = orderService;
        this.unifiedService = unifiedService;
    }*/

    @GetMapping(path="/orders")
    public List<Order> getAllOrder(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return orderService.getAllOrders(thePage,pageable);
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

    @PutMapping(path="/orders")
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    @GetMapping(path="/orders/search/filter")
    public List<Order> searchOrderBy(@RequestParam Optional<String> start, @RequestParam Optional<String> end, @RequestParam Optional<Integer> page){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return orderService.searchOrderBy(fromdate, todate, thePage, pageable);
    }

    /*@GetMapping(path="/orders/{orderID}/details")
    public String getOrderDetails(@PathVariable Long orderID){
        return orderService.getOrderDetails(orderID);
    }*/

    /*@PostMapping(path="/orders/{orderID}/product/{productID}")
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
    }*/
}
