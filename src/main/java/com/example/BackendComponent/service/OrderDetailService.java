package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.exception.OrderDetailAlreadyExistException;
import com.example.BackendComponent.exception.OrderDetailNotFoundException;
import com.example.BackendComponent.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

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
        orderDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
