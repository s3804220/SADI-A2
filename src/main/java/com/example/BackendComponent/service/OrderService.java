package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.OrderAlreadyExistException;
import com.example.BackendComponent.exception.OrderNotFoundException;
import com.example.BackendComponent.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    //public OrderService(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderByID(Long id){
        return orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(id));
    }

    public Order addOrder(Order order){
        if(!orderRepository.existsById(order.getOrderID())){
            orderRepository.save(order);
        }else {
            throw new OrderAlreadyExistException(order.getOrderID());
        }
        return order;
    }

    public Order updateOrder(Order order){
        if(orderRepository.existsById(order.getOrderID())){
            orderRepository.save(order);
        } else{
            throw new OrderNotFoundException(order.getOrderID());
        }
        return order;
    }

    public Order deleteOrder(Long id){
        Order orderToDelete = getOrderByID(id);
        orderRepository.delete(orderToDelete);
        return orderToDelete;
    }

    /*public Order updateOrder(Long id, Order newOrder){
        Order orderToUpdate = getOrderByID(id);
        orderToUpdate.setOrderDate(newOrder.getOrderDate());
        orderToUpdate.setOrderQuantity(newOrder.getOrderQuantity());
        return orderToUpdate;
    }*/


    public String getOrderDetails(Long orderID){
        Order order = getOrderByID(orderID);
        return "Product Info: {" +order.getOrderProduct() + "}" + "\n" + "Staff Info: {" + order.getOrderStaff() + "}" + "\nOrder Quantity: " + order.getOrderQuantity() +
                "\nOrder Total Price: $ " + order.getOrderQuantity()*order.getOrderProduct().getPrice();
    }
}
