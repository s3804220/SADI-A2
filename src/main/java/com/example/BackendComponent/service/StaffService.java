package com.example.BackendComponent.service;

import com.example.BackendComponent.exception.StaffAlreadyExistException;
import com.example.BackendComponent.exception.StaffNotFoundException;
import com.example.BackendComponent.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import com.example.BackendComponent.entity.Staff;

@Transactional
@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    /*@Autowired
    public StaffService(StaffRepository staffRepository, OrderService orderService) {
        this.staffRepository = staffRepository;
    }*/

    public Staff addStaff(Staff staff){
        if(!staffRepository.existsById(staff.getStaffID())){
            staffRepository.save(staff);
        }else {
            throw new StaffAlreadyExistException(staff.getStaffID());
        }
        return staff;
    }

    public Staff updateStaff(Staff staff){
        if(staffRepository.existsById(staff.getStaffID())){
            staffRepository.save(staff);
        } else {
            throw new StaffNotFoundException(staff.getStaffID());
        }
        return staff;
    }

    public List<Staff> getAllStaffs(){
        return staffRepository.findAll();
    }

    public List<Staff> getAllStaffsWithPage(int page){
        Pageable pageable = PageRequest.of(page, 3);
        return staffRepository.findAll(pageable).getContent();
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

    public List<Staff> searchStaffBy(String name, String address, String phone, String email){
        return staffRepository.searchStaffBy(name,address,phone,email);
    }

    /*public Staff updateStaff(Long id, Staff newStaff){
        Staff staffToEdit = getStaffByID(id);
        staffToEdit.setStaffName(newStaff.getStaffName());
        staffToEdit.setStaffPhone(newStaff.getStaffPhone());
        staffToEdit.setStaffAddress(newStaff.getStaffAddress());
        staffToEdit.setStaffEmail(newStaff.getStaffEmail());
        staffToEdit.setStaffAddress(newStaff.getStaffAddress());
        return staffToEdit;
    }*/
}
