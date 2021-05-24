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
    public List<SaleInvoice> getAllSaleInvoices(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return saleInvoiceService.getAllSaleInvoice(thePage, pageable);
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

    @GetMapping(path="/sales/update/{id}")
    public SaleInvoice quickUpdateSaleInvoice(@PathVariable Long id){
        return saleInvoiceService.quickUpdateSaleInvoice(id);
    }

    @GetMapping(path="/sales/search")
    public List<SaleInvoice> searchSaleBy(@RequestParam Optional<String> start, @RequestParam Optional<String> end, @RequestParam Optional<Integer> page){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return saleInvoiceService.searchSaleBy(fromdate, todate, thePage, pageable);
    }

    @GetMapping(path="/sales/staff/{id}/search")
    public List<SaleInvoice> searchSaleByStaff(@PathVariable Long id, @RequestParam Optional<String> start, @RequestParam Optional<String> end, @RequestParam Optional<Integer> page){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return saleInvoiceService.searchSaleByStaff(fromdate, todate, id, thePage, pageable);
    }

    @GetMapping(path="/sales/customer/{id}/search")
    public List<SaleInvoice> searchSaleByCustomer(@PathVariable Long id, @RequestParam Optional<String> start, @RequestParam Optional<String> end, @RequestParam Optional<Integer> page){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return saleInvoiceService.searchSaleByCustomer(fromdate, todate, id, thePage, pageable);
    }
}
