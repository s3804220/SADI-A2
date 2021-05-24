package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.repository.DeliveryNoteRepository;
import com.example.BackendComponent.repository.ProductRepository;
import com.example.BackendComponent.repository.ReceivingNoteRepository;
import com.example.BackendComponent.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Transactional
@Service
public class UnifiedService {
    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;
    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;
    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Object> getRevenue(LocalDate startdate, LocalDate enddate){
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
        List<SaleInvoice> saleInvoiceList = saleInvoiceRepository.searchSaleBy(fromdate, todate);
        BigDecimal revenue = BigDecimal.ZERO;
        for(SaleInvoice saleInvoice : saleInvoiceList){
            revenue = revenue.add(saleInvoice.getTotalPrice());
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        String revenueString = df.format(revenue);
        String totalRevenue = "Total revenue in the selected period: " + revenueString;
        List<Object> resultList = new ArrayList<>();
        resultList.add(totalRevenue);
        resultList.addAll(saleInvoiceList);
        return resultList;
    }

    public List<Object> getRevenueByStaff(LocalDate startdate, LocalDate enddate, Long staffid){
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
        List<SaleInvoice> saleInvoiceList = saleInvoiceRepository.searchSaleByStaff(fromdate, todate, staffid);
        BigDecimal revenue = BigDecimal.ZERO;
        for(SaleInvoice saleInvoice : saleInvoiceList){
            revenue = revenue.add(saleInvoice.getTotalPrice());
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        String revenueString = df.format(revenue);
        String totalRevenue = "Total revenue by this staff in the selected period: " + revenueString;
        List<Object> resultList = new ArrayList<>();
        resultList.add(totalRevenue);
        resultList.addAll(saleInvoiceList);
        return resultList;
    }

    public List<Object> getRevenueByCustomer(LocalDate startdate, LocalDate enddate, Long custid){
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
        List<SaleInvoice> saleInvoiceList = saleInvoiceRepository.searchSaleByCustomer(fromdate, todate, custid);
        BigDecimal revenue = BigDecimal.ZERO;
        for(SaleInvoice saleInvoice : saleInvoiceList){
            revenue = revenue.add(saleInvoice.getTotalPrice());
        }
        DecimalFormat df = new DecimalFormat("#,###.00");
        String revenueString = df.format(revenue);
        String totalRevenue = "Total revenue by this customer in the selected period: " + revenueString;
        List<Object> resultList = new ArrayList<>();
        resultList.add(totalRevenue);
        resultList.addAll(saleInvoiceList);
        return resultList;
    }

    public List<Object> getInventory(LocalDate startdate, LocalDate enddate){
        Date fromdate = null,todate = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d uuuu", Locale.ENGLISH);
        String start = "", end = "";
        if(startdate != null){
            try {
                fromdate = new SimpleDateFormat("yyyy-MM-dd").parse(startdate.toString());
                start = dtf.format(startdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(enddate != null){
            try {
                todate = new SimpleDateFormat("yyyy-MM-dd").parse(enddate.toString());
                end = dtf.format(enddate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String dateString = "Date: "+start +" - " + end;
        List<ReceivingNote> receivingNoteList = receivingNoteRepository.searchReceivingNoteBy(fromdate, todate);
        List<DeliveryNote> deliveryNoteList = deliveryNoteRepository.searchDeliveryNoteBy(fromdate, todate);
        List<Product> productList = productRepository.findAll();
        List<Object> resultList = new ArrayList<>();
        resultList.add(dateString);
        String headerString = String.format("%-8s%-8s%-16s%-8s%-16s%-8s%-16s","Name","|","Received","|","Delivery","|","Balance");
        resultList.add(headerString);
        for(Product product : productList){
            int receivedQty = 0, deliveredQty = 0;
            Set<ReceivingDetail> receivingDetailSet = product.getProductReceivingDetails();
            if(receivingDetailSet != null){
                for(ReceivingDetail receivingDetail : receivingDetailSet){
                    if(receivingDetail.getReceivingNote() != null){
                        if(receivingNoteList.stream().anyMatch(r -> r.getReceivingNoteID().equals(receivingDetail.getReceivingNote().getReceivingNoteID()))){
                            receivedQty += receivingDetail.getReceiveQuantity();
                        }
                    }
                }
            }
            Set<DeliveryDetail> deliveryDetailSet = product.getProductDeliveryDetails();
            if(deliveryDetailSet != null){
                for(DeliveryDetail deliveryDetail : deliveryDetailSet){
                    if(deliveryDetail.getDeliveryNote() != null){
                        if(deliveryNoteList.stream().anyMatch(d -> d.getDeliveryNoteID().equals(deliveryDetail.getDeliveryNote().getDeliveryNoteID()))){
                            deliveredQty += deliveryDetail.getDeliveryQuantity();
                        }
                    }
                }
            }
            int balance = receivedQty - deliveredQty;
            String inventoryString = String.format("Product %-8d%-24d%-24d%-15d", product.getProductID(), receivedQty, deliveredQty, balance);
            resultList.add(inventoryString);
        }
        return resultList;
    }
}