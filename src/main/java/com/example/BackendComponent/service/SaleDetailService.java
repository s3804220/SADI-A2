package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.SaleDetail;
import com.example.BackendComponent.entity.SaleInvoice;
import com.example.BackendComponent.exception.OrderDetailAlreadyExistException;
import com.example.BackendComponent.exception.OrderDetailNotFoundException;
import com.example.BackendComponent.exception.SaleDetailAlreadyExistException;
import com.example.BackendComponent.exception.SaleDetailNotFoundException;
import com.example.BackendComponent.repository.ProductRepository;
import com.example.BackendComponent.repository.SaleDetailRepository;
import com.example.BackendComponent.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Transactional
@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    public List<SaleDetail> getAllSaleDetails(){
        return saleDetailRepository.findAll();
    }

    public SaleDetail getSaleDetailByID(Long id){
        return saleDetailRepository.findById(id).orElseThrow(() ->
                new SaleDetailNotFoundException(id));
    }

    public SaleDetail addSaleDetail(SaleDetail saleDetail){
        if(!saleDetailRepository.existsById(saleDetail.getSaleDetailID())){
            saleDetailRepository.save(saleDetail);
            /*if(saleDetail.getSaleInvoice().getSaleID() != null){
                Long invoiceID = saleDetail.getSaleInvoice().getSaleID();
                SaleInvoice saleInvoice = saleInvoiceRepository.getOne(invoiceID);
                Set<SaleDetail> saleDetailSet = saleInvoice.getSaleDetails();
                saleDetailSet.add(saleDetail);
                if(saleDetailSet != null){
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    for(SaleDetail aDetail : saleDetailSet){
                        System.out.println(aDetail.getSaleProduct().getPrice());
                        System.out.println(aDetail.getSaleQuantity());
                        Product product = productRepository.getOne(aDetail.getSaleProduct().getProductID());
                        BigDecimal productPrice = product.getPrice();
                        int productQuantity = aDetail.getSaleQuantity();
                        BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                        totalPrice = totalPrice.add(totalValue);
                    }
                    saleInvoice.setTotalPrice(totalPrice);
                    //saleInvoiceRepository.save(saleInvoice);
                }
            }*/
        }else {
            throw new SaleDetailAlreadyExistException(saleDetail.getSaleDetailID());
        }
        return saleDetail;
    }

    public SaleDetail updateSaleDetail(SaleDetail saleDetail){
        if(saleDetailRepository.existsById(saleDetail.getSaleDetailID())){
            //SaleDetail detailFromDB = saleDetailRepository.getOne(saleDetail.getSaleDetailID());
            //Long invoiceID = detailFromDB.getSaleInvoice().getSaleID();
            saleDetailRepository.save(saleDetail);
            /*if(saleDetail.getSaleInvoice() != null){
                Long invoiceID = saleDetail.getSaleInvoice().getSaleID();
                SaleInvoice saleInvoice = saleInvoiceRepository.getOne(invoiceID);
                Set<SaleDetail> saleDetailSet = saleInvoice.getSaleDetails();
                if(saleDetailSet != null){
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    for(SaleDetail aDetail : saleDetailSet){
                        BigDecimal productPrice = aDetail.getSaleProduct().getPrice();
                        int productQuantity = aDetail.getSaleQuantity();
                        BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                        totalPrice = totalPrice.add(totalValue);
                    }
                    saleInvoice.setTotalPrice(totalPrice);
                    saleInvoiceRepository.save(saleInvoiceRepository.save(saleInvoice));
                }
            }*/
            /*if(invoiceID != null && !Objects.equals(invoiceID,saleDetail.getSaleInvoice().getSaleID())){
                System.out.println("Old invoice is NOT NULL!");
                SaleInvoice saleInvoice = saleInvoiceRepository.getOne(invoiceID);
                Set<SaleDetail> saleDetailSet = saleInvoice.getSaleDetails();
                if(saleDetailSet != null){
                    System.out.println("Old detail set is NOT NULL!");
                    BigDecimal totalPrice = BigDecimal.ZERO;
                    saleDetailSet.removeIf(aDetail -> aDetail.getSaleDetailID().equals(saleDetail.getSaleDetailID()));
                    for(SaleDetail aDetail : saleDetailSet){
                        BigDecimal productPrice = aDetail.getSaleProduct().getPrice();
                        System.out.println(productPrice);
                        int productQuantity = aDetail.getSaleQuantity();
                        System.out.println(productQuantity);
                        BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                        totalPrice = totalPrice.add(totalValue);
                    }
                    saleInvoice.setTotalPrice(totalPrice);
                    System.out.println("Old total price is"+totalPrice);
                    System.out.println(saleInvoice);
                    saleInvoiceRepository.save(saleInvoice);
                }
            }*/
        } else{
            throw new SaleDetailNotFoundException(saleDetail.getSaleDetailID());
        }
        return saleDetail;
    }

    public SaleDetail deleteSaleDetail(Long id){
        SaleDetail detailToDelete = getSaleDetailByID(id);
        if(detailToDelete.getSaleInvoice() != null){
            Long invoiceID = detailToDelete.getSaleInvoice().getSaleID();
            SaleInvoice saleInvoice = saleInvoiceRepository.getOne(invoiceID);
            Set<SaleDetail> saleDetailSet = saleInvoice.getSaleDetails();
            saleDetailSet.remove(detailToDelete);
            BigDecimal totalPrice = BigDecimal.ZERO;
            if(saleDetailSet != null){
                for(SaleDetail aDetail : saleDetailSet){
                    BigDecimal productPrice = aDetail.getSaleProduct().getPrice();
                    int productQuantity = aDetail.getSaleQuantity();
                    BigDecimal totalValue = productPrice.multiply(BigDecimal.valueOf(productQuantity));
                    totalPrice = totalPrice.add(totalValue);
                }
                saleInvoice.setTotalPrice(totalPrice);
                saleInvoiceRepository.save(saleInvoice);
            }
        }
        saleDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
