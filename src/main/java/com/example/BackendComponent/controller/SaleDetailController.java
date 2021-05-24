package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.SaleDetail;
import com.example.BackendComponent.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SaleDetailController {
    @Autowired
    private SaleDetailService saleDetailService;

    @GetMapping(path="/saledetail")
    public List<SaleDetail> getAllSaleDetails(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return saleDetailService.getAllSaleDetails(thePage, pageable);
    }

    @GetMapping(path="/saledetail/{id}")
    public SaleDetail getSaleDetailByID(@PathVariable Long id){
        return saleDetailService.getSaleDetailByID(id);
    }

    @PostMapping(path="/saledetail")
    public SaleDetail addSaleDetail(@RequestBody SaleDetail saleDetail){
        return saleDetailService.addSaleDetail(saleDetail);
    }

    @DeleteMapping(path="/saledetail/{id}")
    public SaleDetail deleteSaleDetail(@PathVariable Long id){
        return saleDetailService.deleteSaleDetail(id);
    }

    @PutMapping(path="/saledetail")
    public SaleDetail updateSaleDetail(@RequestBody SaleDetail saleDetail){
        return saleDetailService.updateSaleDetail(saleDetail);
    }
}
