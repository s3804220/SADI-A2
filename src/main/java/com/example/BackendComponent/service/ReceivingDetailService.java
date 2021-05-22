package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.exception.ReceivingDetailAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingDetailNotFoundException;
import com.example.BackendComponent.exception.ReceivingNoteAlreadyExistException;
import com.example.BackendComponent.exception.ReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.ReceivingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ReceivingDetailService {
    @Autowired
    private ReceivingDetailRepository receivingDetailRepository;

    public ReceivingDetail addReceivingDetail(ReceivingDetail receivingDetail){
        if (!receivingDetailRepository.existsById(receivingDetail.getReceivingDetailID())){
            receivingDetailRepository.save(receivingDetail);
        }else{
            throw new ReceivingDetailAlreadyExistException(receivingDetail.getReceivingDetailID());
        }
        return receivingDetail;
    }

    public ReceivingDetail updateReceivingDetail(ReceivingDetail receivingDetail){
        if (receivingDetailRepository.existsById(receivingDetail.getReceivingDetailID())){
            receivingDetailRepository.save(receivingDetail);
        }else {
            throw new ReceivingDetailNotFoundException(receivingDetail.getReceivingDetailID());
        }
        return receivingDetail;
    }

    public List<ReceivingDetail> getAllReceivingDetails(){
        return receivingDetailRepository.findAll();
    }

    public ReceivingDetail getReceivingDetailByID(Long id){
        return receivingDetailRepository.findById(id).orElseThrow(() ->
                new ReceivingDetailNotFoundException(id));
    }

    public ReceivingDetail deleteReceivingDetail(Long id){
        ReceivingDetail detailToDelete = getReceivingDetailByID(id);
        receivingDetailRepository.delete(detailToDelete);
        return detailToDelete;
    }
}
