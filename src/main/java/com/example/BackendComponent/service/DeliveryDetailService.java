package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.DeliveryDetail;
import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.exception.*;
import com.example.BackendComponent.repository.DeliveryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DeliveryDetailService {
    @Autowired
    private DeliveryDetailRepository deliveryDetailRepository;

    public DeliveryDetail addDeliveryDetail(DeliveryDetail deliveryDetail){
        if (!deliveryDetailRepository.existsById(deliveryDetail.getDeliveryDetailID())){
            deliveryDetailRepository.save(deliveryDetail);
        }else{
            throw new DeliveryDetailAlreadyExistException(deliveryDetail.getDeliveryDetailID());
        }
        return deliveryDetail;
    }

    public DeliveryDetail updateDeliveryDetail(DeliveryDetail deliveryDetail){
        if (deliveryDetailRepository.existsById(deliveryDetail.getDeliveryDetailID())){
            deliveryDetailRepository.save(deliveryDetail);
        }else {
            throw new DeliveryDetailNotFoundException(deliveryDetail.getDeliveryDetailID());
        }
        return deliveryDetail;
    }

    public List<DeliveryDetail> getAllDeliveryDetails(){
        return deliveryDetailRepository.findAll();
    }

    public DeliveryDetail getDeliveryDetailByID(Long id){
        return deliveryDetailRepository.findById(id).orElseThrow(() ->
                new DeliveryDetailNotFoundException(id));
    }

    public DeliveryDetail deleteDeliveryDetail(Long id){
        DeliveryDetail detailToDelete = getDeliveryDetailByID(id);
        deliveryDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
