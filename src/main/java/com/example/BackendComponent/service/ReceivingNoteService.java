package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.exception.OrderNotFoundException;
import com.example.BackendComponent.exception.ReceivingNoteAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.ReceivingDetailRepository;
import com.example.BackendComponent.repository.ReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Transactional
@Service
public class ReceivingNoteService {
    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;
    @Autowired
    private ReceivingDetailRepository receivingDetailRepository;

    public ReceivingNote addReceivingNote(ReceivingNote receivingNote){
        if (!receivingNoteRepository.existsById(receivingNote.getReceivingNoteID())){
            receivingNoteRepository.save(receivingNote);
            if(receivingNote.getReceiveOrder() != null){
                Set<OrderDetail> orderDetailSet = receivingNote.getReceiveOrder().getOrderDetails();
                for(OrderDetail orderDetail : orderDetailSet){
                    ReceivingDetail newReceivingDetail = new ReceivingDetail();
                    newReceivingDetail.setReceivingNote(receivingNote);
                    newReceivingDetail.setReceivingDetailID(orderDetail.getOrderDetailID());
                    newReceivingDetail.setReceiveProduct(orderDetail.getOrderProduct());
                    newReceivingDetail.setReceiveQuantity(orderDetail.getOrderQuantity());
                    receivingDetailRepository.save(newReceivingDetail);
                }
            }
        }else{
            throw new ReceivingNoteAlreadyExistException(receivingNote.getReceivingNoteID());
        }
        return receivingNote;
    }

    public ReceivingNote updateReceivingNote(ReceivingNote receivingNote){
        if (receivingNoteRepository.existsById(receivingNote.getReceivingNoteID())){
            if(!Objects.equals(receivingNoteRepository.getOne(receivingNote.getReceivingNoteID()).getReceiveOrder().getOrderID(),receivingNote.getReceiveOrder().getOrderID())){
                Set<ReceivingDetail> receivingDetailSet = receivingNoteRepository.getOne(receivingNote.getReceivingNoteID()).getReceivingDetails();
                for(ReceivingDetail receivingDetail : receivingDetailSet){
                    receivingDetailRepository.delete(receivingDetail);
                }
                receivingNoteRepository.save(receivingNote);
                if(receivingNote.getReceiveOrder() != null){
                    Set<OrderDetail> orderDetailSet = receivingNote.getReceiveOrder().getOrderDetails();
                    for(OrderDetail orderDetail : orderDetailSet){
                        ReceivingDetail newReceivingDetail = new ReceivingDetail();
                        newReceivingDetail.setReceivingNote(receivingNote);
                        newReceivingDetail.setReceivingDetailID(orderDetail.getOrderDetailID());
                        newReceivingDetail.setReceiveProduct(orderDetail.getOrderProduct());
                        newReceivingDetail.setReceiveQuantity(orderDetail.getOrderQuantity());
                        receivingDetailRepository.save(newReceivingDetail);
                    }
                }
            }else{
                receivingNoteRepository.save(receivingNote);
            }
        }else {
            throw new ReceivingNoteNotFoundException(receivingNote.getReceivingNoteID());
        }
        return receivingNote;
    }

    public List<ReceivingNote> getAllReceivingNotes(){
        return receivingNoteRepository.findAll();
    }

    public ReceivingNote getReceivingNoteByID(Long id){
        return receivingNoteRepository.findById(id).orElseThrow(() ->
                new ReceivingNoteNotFoundException(id));
    }

    public ReceivingNote deleteReceivingNote(Long id){
        ReceivingNote receivingNoteToDelete = getReceivingNoteByID(id);
        receivingNoteRepository.delete(receivingNoteToDelete);
        return receivingNoteToDelete;
    }

    public List<ReceivingNote> searchReceivingNoteBy(LocalDate startdate, LocalDate enddate){
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
        return receivingNoteRepository.searchReceivingNoteBy(fromdate, todate);
    }
}
