package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.service.UnifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/stats")
public class UnifiedController {
    @Autowired
    private UnifiedService unifiedService;

    @GetMapping(path="/revenue")
    public List<Object> getRevenue(@RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return unifiedService.getRevenue(fromdate, todate);
    }

    @GetMapping(path="/revenue/staff/{id}")
    public List<Object> getRevenueByStaff(@PathVariable Long id, @RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return unifiedService.getRevenueByStaff(fromdate, todate, id);
    }

    @GetMapping(path="/revenue/customer/{id}")
    public List<Object> getRevenueByCustomer(@PathVariable Long id, @RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return unifiedService.getRevenueByCustomer(fromdate, todate, id);
    }

    @GetMapping(path="/inventory")
    public List<Object> getInventory(@RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return unifiedService.getInventory(fromdate, todate);
    }
}
