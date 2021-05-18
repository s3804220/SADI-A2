package com.example.BackendComponent.controller;

import java.util.List;

import com.example.BackendComponent.entity.Staff;
import com.example.BackendComponent.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping(path="/staffs")
    public List<Staff> getAllStaffs(){return staffService.getAllStaffs();}

    @GetMapping(path="/staffs/{id}")
    public Staff getStaffById(@PathVariable Long id){return staffService.getStaffByID(id);}

    @PostMapping(path="/staffs")
    public Staff addStaff(@RequestBody Staff staff){return staffService.addStaff(staff);}

    @DeleteMapping(path="/staffs/{id}")
    public Staff deleteStaff(@PathVariable Long id){return staffService.deleteStaff(id);}

    @PutMapping(path="/staffs/{id}")
    public Staff updateStaff(@PathVariable final Long id, @RequestBody final Staff staff){
        return staffService.updateStaff(id, staff);
    }
}
