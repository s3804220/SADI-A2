package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.exception.OrderDetailAlreadyExistException;
import com.example.BackendComponent.exception.OrderDetailNotFoundException;
import com.example.BackendComponent.repository.OrderDetailRepository;
import com.example.BackendComponent.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDetail> getAllOrderDetails(){
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailByID(Long id){
        return orderDetailRepository.findById(id).orElseThrow(() ->
                new OrderDetailNotFoundException(id));
    }

    public OrderDetail addOrderDetail(OrderDetail orderDetail){
        if(!orderDetailRepository.existsById(orderDetail.getOrderDetailID())){
            orderDetailRepository.save(orderDetail);
        }else {
            throw new OrderDetailAlreadyExistException(orderDetail.getOrderDetailID());
        }
        return orderDetail;
    }

    public OrderDetail updateOrderDetail(OrderDetail orderDetail){
        if(orderDetailRepository.existsById(orderDetail.getOrderDetailID())){
            orderDetailRepository.save(orderDetail);
        } else{
            throw new OrderDetailNotFoundException(orderDetail.getOrderDetailID());
        }
        return orderDetail;
    }

    public OrderDetail deleteOrderDetail(Long id){
        OrderDetail detailToDelete = getOrderDetailByID(id);
        if(detailToDelete.getOrder() != null){
            Long orderID = detailToDelete.getOrder().getOrderID();
            Order order = orderRepository.getOne(orderID);
            Set<OrderDetail> orderDetailSet = order.getOrderDetails();
            orderDetailSet.remove(detailToDelete);
        }
        orderDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
