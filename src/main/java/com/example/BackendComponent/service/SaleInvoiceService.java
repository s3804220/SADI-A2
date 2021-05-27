package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.SaleDetail;
import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.exception.SaleInvoiceAlreadyExistException;
import com.example.BackendComponent.exception.SaleInvoiceNotFoundException;
import com.example.BackendComponent.repository.ProductRepository;
import com.example.BackendComponent.repository.SaleDetailRepository;
import com.example.BackendComponent.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
            /*SaleInvoice saleInvoice1 = saleInvoiceRepository.getOne(saleInvoice.getSaleID());
            BigDecimal totalPrice = BigDecimal.ZERO;
            if(saleInvoice1.getSaleDetails() != null){
                Set<SaleDetail> saleDetailSet = saleInvoice1.getSaleDetails();
                for(SaleDetail saleDetail : saleDetailSet){
                    BigDecimal productPrice = saleDetail.getSaleProduct().getPrice();
                    int productQuantity = saleDetail.getSaleQuantity();
                    BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                    totalPrice = totalPrice.add(totalValue);
                }
            }
            saleInvoice.setTotalPrice(totalPrice);*/
            /*BigDecimal totalPrice = BigDecimal.ZERO;
            if(saleInvoice.getSaleDetails() != null){
                Set<SaleDetail> saleDetailSet = saleInvoice.getSaleDetails();
                for(SaleDetail saleDetail : saleDetailSet){
                    BigDecimal productPrice = BigDecimal.ZERO;
                    int productQuantity;
                    if(saleDetailRepository.existsById(saleDetail.getSaleDetailID()) && saleDetail.getSaleQuantity() == 0 && saleDetail.getSaleProduct() == null){
                        SaleDetail saleDetail1 = saleDetailRepository.getOne(saleDetail.getSaleDetailID());
                        Product product = productRepository.getOne(saleDetail1.getSaleProduct().getProductID());
                        productPrice = product.getPrice();
                        productQuantity = saleDetail1.getSaleQuantity();
                    }else{
                        Product product = productRepository.getOne(saleDetail.getSaleProduct().getProductID());
                        productPrice = product.getPrice();
                        productQuantity = saleDetail.getSaleQuantity();
                    }
                    BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                    totalPrice = totalPrice.add(totalValue);
                }
            }*/
            saleInvoiceRepository.save(saleInvoice);
        }else {
            throw new SaleInvoiceNotFoundException(saleInvoice.getSaleID());
        }
        return saleInvoice;
    }

    public SaleInvoice quickUpdateSaleInvoice(Long id){
        SaleInvoice saleInvoiceToUpdate = getSaleInvoiceById(id);
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(saleInvoiceToUpdate.getSaleDetails() != null){
            Set<SaleDetail> saleDetailSet = saleInvoiceToUpdate.getSaleDetails();
            for(SaleDetail saleDetail : saleDetailSet){
                BigDecimal productPrice = BigDecimal.ZERO;
                if(saleDetail.getSaleProduct() != null){
                    productPrice = saleDetail.getSaleProduct().getPrice();
                }
                int productQuantity = saleDetail.getSaleQuantity();
                BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                totalPrice = totalPrice.add(totalValue);
            }
        }
        saleInvoiceToUpdate.setTotalPrice(totalPrice);
        saleInvoiceRepository.save(saleInvoiceToUpdate);
        return saleInvoiceToUpdate;
    }

    public List<SaleInvoice> getAllSaleInvoice(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return saleInvoiceRepository.findAll(pageable).getContent();
    }

    public SaleInvoice getSaleInvoiceById(Long id){
        return saleInvoiceRepository.findById(id).orElseThrow(() ->
                new SaleInvoiceNotFoundException(id));
    }

    public SaleInvoice deleteSaleInvoice(Long id){
        SaleInvoice saleInvoiceToDelete = getSaleInvoiceById(id);
        saleInvoiceRepository.delete(saleInvoiceToDelete);
        return saleInvoiceToDelete;
    }

    public List<SaleInvoice> searchSaleBy(LocalDate startdate, LocalDate enddate, int page, boolean pageBool){
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
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return saleInvoiceRepository.searchSaleBy(fromdate, todate, pageable).getContent();
    }

    public List<SaleInvoice> searchSaleByStaff(LocalDate startdate, LocalDate enddate, Long staffid, int page, boolean pageBool){
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
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return saleInvoiceRepository.searchSaleByStaff(fromdate, todate, staffid, pageable).getContent();
    }

    public List<SaleInvoice> searchSaleByCustomer(LocalDate startdate, LocalDate enddate, Long custid, int page, boolean pageBool){
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
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return saleInvoiceRepository.searchSaleByCustomer(fromdate, todate, custid, pageable).getContent();
    }
}
