package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.exception.StaffNotFoundException;
import com.example.BackendComponent.repository.StaffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.BackendComponent.entity.Staff;

@Transactional
@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final OrderService orderService;

    @Autowired
    public StaffService(StaffRepository staffRepository, OrderService orderService) {
        this.staffRepository = staffRepository;
        this.orderService = orderService;
    }

    public Staff addStaff(Staff staff){
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaffs(){
        return StreamSupport
                .stream(staffRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Staff getStaffByID(Long id){
        return staffRepository.findById(id).orElseThrow(() ->
                new StaffNotFoundException(id));
    }

    public Staff deleteStaff(Long id){
        Staff staff = getStaffByID(id);
        staffRepository.delete(staff);
        return staff;
    }

    public Staff updateStaff(Long id, Staff newStaff){
        Staff staffToEdit = getStaffByID(id);
        staffToEdit.setStaffName(newStaff.getStaffName());
        staffToEdit.setStaffPhone(newStaff.getStaffPhone());
        staffToEdit.setStaffAddress(newStaff.getStaffAddress());
        staffToEdit.setStaffEmail(newStaff.getStaffEmail());
        staffToEdit.setStaffAddress(newStaff.getStaffAddress());

        return staffToEdit;
    }

    public Staff addStaffToOrder(Long staffID, Long orderID){
        Staff staff = getStaffByID(staffID);
        Order order = orderService.getOrderByID(orderID);
        staff.addStaffOrders(order);
        order.setOrderStaff(staff);
        return staff;
    }

    public Staff removeStaffFromOrder(Long staffID, Long orderID){
        Staff staff = getStaffByID(staffID);
        Order order = orderService.getOrderByID(orderID);
        staff.deleteStaffOrders(order);
        order.deleteOrderStaff();
        return staff;
    }
}
