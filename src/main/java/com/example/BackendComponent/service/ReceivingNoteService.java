package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.OrderDetail;
import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.exception.OrderNotFoundException;
import com.example.BackendComponent.exception.ReceivingNoteAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.OrderRepository;
import com.example.BackendComponent.repository.ReceivingDetailRepository;
import com.example.BackendComponent.repository.ReceivingNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class ReceivingNoteService {
    @Autowired
    private ReceivingNoteRepository receivingNoteRepository;
    @Autowired
    private ReceivingDetailRepository receivingDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ReceivingNote addReceivingNote(ReceivingNote receivingNote){
        if (!receivingNoteRepository.existsById(receivingNote.getReceivingNoteID())){
            receivingNoteRepository.save(receivingNote);
            if(receivingNote.getReceiveOrder() != null){
                Long orderIdToTransfer = receivingNote.getReceiveOrder().getOrderID();
                Order orderToTransfer = orderRepository.getOne(orderIdToTransfer);
                Set<OrderDetail> orderDetailSet = orderToTransfer.getOrderDetails();
                if(orderDetailSet != null){
                    for(OrderDetail orderDetail : orderDetailSet){
                        ReceivingDetail newReceivingDetail = new ReceivingDetail();
                        newReceivingDetail.setReceivingNote(receivingNote);
                        newReceivingDetail.setReceivingDetailID(orderDetail.getOrderDetailID());
                        newReceivingDetail.setReceiveProduct(orderDetail.getOrderProduct());
                        newReceivingDetail.setReceiveQuantity(orderDetail.getOrderQuantity());
                        receivingDetailRepository.save(newReceivingDetail);
                    }
                }
            }
        }else{
            throw new ReceivingNoteAlreadyExistException(receivingNote.getReceivingNoteID());
        }
        return receivingNote;
    }

    public ReceivingNote updateReceivingNote(ReceivingNote receivingNote){
        if (receivingNoteRepository.existsById(receivingNote.getReceivingNoteID())){
            Set<ReceivingDetail> receivingDetailSet = receivingNoteRepository.getOne(receivingNote.getReceivingNoteID()).getReceivingDetails();
            Set<ReceivingDetail> tempSet = new HashSet<>();
            receivingNoteRepository.save(receivingNote);
            if(receivingDetailSet != null){
                tempSet.addAll(receivingDetailSet);
                receivingDetailSet.clear();
                for(ReceivingDetail aDetail: tempSet){
                    receivingDetailRepository.delete(aDetail);
                }
            }
            if(receivingNote.getReceiveOrder() != null){
                Long orderIdToTransfer = receivingNote.getReceiveOrder().getOrderID();
                Order orderToTransfer = orderRepository.getOne(orderIdToTransfer);
                Set<OrderDetail> orderDetailSet = orderToTransfer.getOrderDetails();
                if(orderDetailSet != null){
                    for(OrderDetail orderDetail : orderDetailSet){
                        ReceivingDetail newReceivingDetail = new ReceivingDetail();
                        newReceivingDetail.setReceivingNote(receivingNote);
                        newReceivingDetail.setReceivingDetailID(orderDetail.getOrderDetailID());
                        newReceivingDetail.setReceiveProduct(orderDetail.getOrderProduct());
                        newReceivingDetail.setReceiveQuantity(orderDetail.getOrderQuantity());
                        receivingDetailRepository.save(newReceivingDetail);
                    }
                }
            }
        }else {
            throw new ReceivingNoteNotFoundException(receivingNote.getReceivingNoteID());
        }
        return receivingNote;
    }

    public ReceivingNote quickUpdateReceivingNote(Long id){
        ReceivingNote noteToUpdate = getReceivingNoteByID(id);
        return updateReceivingNote(noteToUpdate);
    }

    public List<ReceivingNote> getAllReceivingNotes(int page, boolean pageBool){
        Pageable pageable;
        if(pageBool){
            pageable = PageRequest.of(page, 3);
        }else{
            pageable = Pageable.unpaged();
        }
        return receivingNoteRepository.findAll(pageable).getContent();
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

    public List<ReceivingNote> searchReceivingNoteBy(LocalDate startdate, LocalDate enddate, int page, boolean pageBool){
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
        return receivingNoteRepository.searchReceivingNoteBy(fromdate, todate, pageable).getContent();
    }
}
