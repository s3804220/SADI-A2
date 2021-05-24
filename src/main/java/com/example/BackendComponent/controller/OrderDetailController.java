package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(path="/orderdetail")
    public List<OrderDetail> getAllOrderDetails(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return orderDetailService.getAllOrderDetails(thePage,pageable);
    }

    @GetMapping(path="/orderdetail/{id}")
    public OrderDetail getOrderDetailByID(@PathVariable Long id){
        return orderDetailService.getOrderDetailByID(id);
    }

    @PostMapping(path="/orderdetail")
    public OrderDetail addOrderDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.addOrderDetail(orderDetail);
    }

    @DeleteMapping(path="/orderdetail/{id}")
    public OrderDetail deleteOrderDetail(@PathVariable Long id){
        return orderDetailService.deleteOrderDetail(id);
    }

    @PutMapping(path="/orderdetail")
    public OrderDetail updateOrderDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.updateOrderDetail(orderDetail);
    }
}
