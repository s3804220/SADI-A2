package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.DeliveryDetail;
import com.example.BackendComponent.entity.DeliveryNote;
import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.exception.*;
import com.example.BackendComponent.repository.DeliveryDetailRepository;
import com.example.BackendComponent.repository.DeliveryNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class DeliveryDetailService {
    @Autowired
    private DeliveryDetailRepository deliveryDetailRepository;
    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

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

    public List<DeliveryDetail> getAllDeliveryDetails(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return deliveryDetailRepository.findAll(pageable).getContent();
    }

    public DeliveryDetail getDeliveryDetailByID(Long id){
        return deliveryDetailRepository.findById(id).orElseThrow(() ->
                new DeliveryDetailNotFoundException(id));
    }

    public DeliveryDetail deleteDeliveryDetail(Long id){
        DeliveryDetail detailToDelete = getDeliveryDetailByID(id);
        if(detailToDelete.getDeliveryNote() != null){
            Long noteID = detailToDelete.getDeliveryNote().getDeliveryNoteID();
            DeliveryNote deliveryNote = deliveryNoteRepository.getOne(noteID);
            Set<DeliveryDetail> deliveryDetailSet = deliveryNote.getDeliveryDetails();
            deliveryDetailSet.remove(detailToDelete);
        }
        deliveryDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
