package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.OrderAlreadyExistException;
import com.example.BackendComponent.exception.OrderNotFoundException;
import com.example.BackendComponent.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 5);
        }else{
            pageable = Pageable.unpaged();
        }
        return orderRepository.findAll(pageable).getContent();
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

    public List<Order> searchOrderBy(LocalDate startdate, LocalDate enddate, int page, boolean pageBool){
        Date fromdate = null,todate = null;
        if(startdate != null){
            try {
                fromdate = new SimpleDateFormat("yyyy-MM-dd").parse(startdate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(enddate != null){
            try {
                todate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 5);
        }else{
            pageable = Pageable.unpaged();
        }
        return orderRepository.searchOrderBy(fromdate, todate, pageable).getContent();
    }

    /*public String getOrderDetails(Long orderID){
        Order order = getOrderByID(orderID);
        return "Product Info: {" +order.getOrderProduct() + "}" + "\n" + "Staff Info: {" + order.getOrderStaff() + "}" + "\nOrder Quantity: " + order.getOrderQuantity() +
                "\nOrder Total Price: $ " + order.getOrderProduct().getPrice().multiply(BigDecimal.valueOf(order.getOrderQuantity()));
    }*/
}
