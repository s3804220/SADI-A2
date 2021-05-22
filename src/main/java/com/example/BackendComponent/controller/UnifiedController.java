package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.service.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/stats")
public class UnifiedController {
    @Autowired
    private UnifiedService unifiedService;

    @GetMapping(path="/sales/staff")
    public List<SaleInvoice> searchSaleByStaff(@RequestParam Optional<String> start, @RequestParam Optional<String> end, @RequestParam Long staffid){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return unifiedService.searchSaleByStaff(fromdate, todate, staffid);
    }
}
