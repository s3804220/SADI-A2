package com.example.BackendComponent.controller;

import java.util.List;

import com.example.BackendComponent.entity.Staff;
import com.example.BackendComponent.service.StaffService;
import com.example.BackendComponent.service.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class StaffController {
    private final StaffService staffService;
    private final UnifiedService unifiedService;

    @Autowired
    public StaffController(StaffService staffService, UnifiedService unifiedService) {
        this.staffService = staffService;
        this.unifiedService = unifiedService;
    }

    @GetMapping(path="/staffs")
    public List<Staff> getAllStaffs(){return staffService.getAllStaffs(null);}

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

    @RequestMapping(path="/staffs/search")
    public List<Staff> searchStaff(@Param("keyword") String keyword){
        return staffService.getAllStaffs(keyword);
    }
}
