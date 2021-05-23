package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.entity.SaleDetail;
import com.example.BackendComponent.exception.OrderDetailAlreadyExistException;
import com.example.BackendComponent.exception.OrderDetailNotFoundException;
import com.example.BackendComponent.exception.SaleDetailAlreadyExistException;
import com.example.BackendComponent.exception.SaleDetailNotFoundException;
import com.example.BackendComponent.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository saleDetailRepository;

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
        }else {
            throw new SaleDetailAlreadyExistException(saleDetail.getSaleDetailID());
        }
        return saleDetail;
    }

    public SaleDetail updateSaleDetail(SaleDetail saleDetail){
        if(saleDetailRepository.existsById(saleDetail.getSaleDetailID())){
            saleDetailRepository.save(saleDetail);
        } else{
            throw new SaleDetailNotFoundException(saleDetail.getSaleDetailID());
        }
        return saleDetail;
    }

    public SaleDetail deleteSaleDetail(Long id){
        SaleDetail detailToDelete = getSaleDetailByID(id);
        saleDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
