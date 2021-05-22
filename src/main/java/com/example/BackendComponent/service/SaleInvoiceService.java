package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.exception.SaleInvoiceAlreadyExistException;
import com.example.BackendComponent.exception.SaleInvoiceNotFoundException;
import com.example.BackendComponent.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class SaleInvoiceService {
    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    public SaleInvoice addSaleInvoice(SaleInvoice saleInvoice){
        if(!saleInvoiceRepository.existsById(saleInvoice.getSaleID())){
            saleInvoiceRepository.save(saleInvoice);
        }else {
            throw new SaleInvoiceAlreadyExistException(saleInvoice.getSaleID());
        }
        return saleInvoice;
    }

    public SaleInvoice updateSaleInvoice(SaleInvoice saleInvoice){
        if(saleInvoiceRepository.existsById(saleInvoice.getSaleID())){
            saleInvoiceRepository.save(saleInvoice);
        }else {
            throw new SaleInvoiceNotFoundException(saleInvoice.getSaleID());
        }
        return saleInvoice;
    }

    public List<SaleInvoice> getAllSaleInvoice(){ return saleInvoiceRepository.findAll(); }

    public SaleInvoice getSaleInvoiceById(Long id){
        return saleInvoiceRepository.findById(id).orElseThrow(() ->
                new SaleInvoiceNotFoundException(id));
    }

    public SaleInvoice deleteSaleInvoice(Long id){
        SaleInvoice saleInvoiceToDelete = getSaleInvoiceById(id);
        saleInvoiceRepository.delete(saleInvoiceToDelete);
        return saleInvoiceToDelete;
    }

    public List<SaleInvoice> searchSaleBy(LocalDate startdate, LocalDate enddate){
        Date fromdate = null,todate = null;
        if(startdate != null){
            try {
                fromdate = new SimpleDateFormat("yyyy-MM-dd").parse(startdate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(enddate != null){
            try {
                todate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return saleInvoiceRepository.searchSaleBy(fromdate, todate);
    }
}
