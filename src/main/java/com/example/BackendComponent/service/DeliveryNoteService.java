package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.DeliveryNote;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.exception.DeliveryNoteNotFoundException;
import com.example.BackendComponent.exception.ReceivingNoteAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.DeliveryNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DeliveryNoteService {
    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNote addDeliveryNote(DeliveryNote deliveryNote){
        if (!deliveryNoteRepository.existsById(deliveryNote.getDeliveryNoteID())){
            deliveryNoteRepository.save(deliveryNote);
        }else{
            throw new ReceivingNoteAlreadyExistException(deliveryNote.getDeliveryNoteID());
        }
        return deliveryNote;
    }

    public DeliveryNote updateDeliveryNote(DeliveryNote deliveryNote){
        if (deliveryNoteRepository.existsById(deliveryNote.getDeliveryNoteID())){
            deliveryNoteRepository.save(deliveryNote);
        }else {
            throw new ReceivingNoteNotFoundException(deliveryNote.getDeliveryNoteID());
        }
        return deliveryNote;
    }

    public List<DeliveryNote> getAllDeliveryNotes(){
        return deliveryNoteRepository.findAll();
    }

    public DeliveryNote getDeliveryNoteByID(Long id){
        return deliveryNoteRepository.findById(id).orElseThrow(() ->
                new DeliveryNoteNotFoundException(id));
    }

    public DeliveryNote deleteDeliveryNote(Long id){
        DeliveryNote deliveryNoteToDelete = getDeliveryNoteByID(id);
        deliveryNoteRepository.delete(deliveryNoteToDelete);
        return deliveryNoteToDelete;
    }
}
