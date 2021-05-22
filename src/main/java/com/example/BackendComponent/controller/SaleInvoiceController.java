package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class SaleInvoiceController {
    @Autowired
    private SaleInvoiceService saleInvoiceService;

    @GetMapping(path="/sales")
    public List<SaleInvoice> getAllSaleInvoices(){
        return saleInvoiceService.getAllSaleInvoice();
    }

    @GetMapping(path="/sales/{id}")
    public SaleInvoice getSaleInvoiceById(@PathVariable Long id){
        return saleInvoiceService.getSaleInvoiceById(id);
    }

    @PostMapping(path="/sales")
    public SaleInvoice addSaleInvoice(@RequestBody SaleInvoice saleInvoice){
        return saleInvoiceService.addSaleInvoice(saleInvoice);
    }

    @DeleteMapping(path="/sales/{id}")
    public SaleInvoice deleteSaleInvoice(@PathVariable Long id){
        return saleInvoiceService.deleteSaleInvoice(id);
    }

    @PutMapping(path="/sales")
    public SaleInvoice updateSaleInvoice(@RequestBody SaleInvoice saleInvoice){
        return saleInvoiceService.updateSaleInvoice(saleInvoice);
    }

    @GetMapping(path="/sales/search")
    public List<SaleInvoice> searchSaleBy(@RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return saleInvoiceService.searchSaleBy(fromdate, todate);
    }
}
