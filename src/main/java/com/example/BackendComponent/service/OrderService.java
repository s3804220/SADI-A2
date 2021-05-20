package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.OrderNotFoundException;
import com.example.BackendComponent.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return StreamSupport
                .stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Order getOrderByID(Long id){
        return orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(id));
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public Order deleteOrder(Long id){
        Order orderToDelete = getOrderByID(id);
        orderRepository.delete(orderToDelete);
        return orderToDelete;
    }

    public Order updateOrder(Long id, Order newOrder){
        Order orderToUpdate = getOrderByID(id);
        orderToUpdate.setOrderDate(newOrder.getOrderDate());
        orderToUpdate.setOrderQuantity(newOrder.getOrderQuantity());
        return orderToUpdate;
    }


    public String getOrderDetails(Long orderID){
        Order order = getOrderByID(orderID);
        return "Product Info: {" +order.getOrderProduct() + "}" + "\n" + "Staff Info: {" + order.getOrderStaff() + "}" + "\nOrder Quantity: " + order.getOrderQuantity() +
                "\nOrder Total Price: $ " + order.getOrderQuantity()*order.getOrderProduct().getPrice();
    }
}
