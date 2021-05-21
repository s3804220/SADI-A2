package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.service.SaleInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class SaleInvoiceController {
    @Autowired
    private SaleInvoiceService saleInvoiceService;

    @GetMapping(path="/sale")
    public List<SaleInvoice> getAllSaleInvoices(){
        return saleInvoiceService.getAllSaleInvoice();
    }

    @GetMapping(path="/sale/{id}")
    public SaleInvoice getSaleInvoiceById(@PathVariable Long id){
        return saleInvoiceService.getSaleInvoiceById(id);
    }

    @PostMapping(path="/sale")
    public SaleInvoice addSaleInvoice(@RequestBody SaleInvoice saleInvoice){
        return saleInvoiceService.addSaleInvoice(saleInvoice);
    }

    @DeleteMapping(path="/sale/{id}")
    public SaleInvoice deleteSaleInvoice(@PathVariable Long id){
        return saleInvoiceService.deleteSaleInvoice(id);
    }

    @PutMapping(path="/sale")
    public SaleInvoice updateSaleInvoice(@RequestBody SaleInvoice saleInvoice){
        return saleInvoiceService.updateSaleInvoice(saleInvoice);
    }
}
