package com.example.BackendComponent.controller;

import java.util.List;
import java.util.Optional;

import com.example.BackendComponent.entity.Staff;
import com.example.BackendComponent.repository.StaffRepository;
import com.example.BackendComponent.service.StaffService;
//import com.example.BackendComponent.service.UnifiedService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /*@Autowired
    public StaffController(StaffService staffService, UnifiedService unifiedService) {
        this.staffService = staffService;
        this.unifiedService = unifiedService;
    }*/

    @GetMapping(path="/staffs")
    public List<Staff> getAllStaffs(){return staffService.getAllStaffs();}

    @GetMapping(path="/staffs/{id}")
    public Staff getStaffById(@PathVariable Long id){return staffService.getStaffByID(id);}

    @PostMapping(path="/staffs")
    public Staff addStaff(@RequestBody Staff staff){return staffService.addStaff(staff);}

    @DeleteMapping(path="/staffs/{id}")
    public Staff deleteStaff(@PathVariable Long id){return staffService.deleteStaff(id);}

    @PutMapping(path="/staffs")
    public Staff updateStaff(@RequestBody Staff staff){
        return staffService.updateStaff(staff);
    }

    /*@RequestMapping(path="/staffs/search")
    public List<Staff> searchStaff(@Param("keyword") String keyword){
        return staffService.getAllStaffs(keyword);
    }*/

    @GetMapping(path="/staffs/search")
    public List<Staff> searchStaffBy(@RequestParam Optional<String> name, @RequestParam Optional<String> address, @RequestParam Optional<String> phone, @RequestParam Optional<String> email){
        String newname, newaddress, newphone, newemail;
        newname = name.orElse(null);
        newaddress = address.orElse(null);
        newphone = phone.orElse(null);
        newemail = email.orElse(null);
        return staffService.searchStaffBy(newname, newaddress, newphone, newemail);
    }
}
