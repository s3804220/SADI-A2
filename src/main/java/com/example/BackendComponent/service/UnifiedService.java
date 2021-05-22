package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.SaleInvoice;
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
public class UnifiedService {
    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    public List<SaleInvoice> searchSaleByStaff(LocalDate startdate, LocalDate enddate, Long staffid){
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
        return saleInvoiceRepository.searchSaleByStaff(fromdate, todate, staffid);
    }
}