package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.DeliveryNote;
import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.exception.DeliveryNoteNotFoundException;
import com.example.BackendComponent.exception.ReceivingNoteAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.DeliveryNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    public List<DeliveryNote> getAllDeliveryNotes(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return deliveryNoteRepository.findAll(pageable).getContent();
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

    public List<DeliveryNote> searchDeliveryNoteBy(LocalDate startdate, LocalDate enddate, int page, boolean pageBool){
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
        return deliveryNoteRepository.searchDeliveryNoteBy(fromdate, todate, pageable).getContent();
    }
}
