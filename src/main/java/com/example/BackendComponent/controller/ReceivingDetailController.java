package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.service.ReceivingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReceivingDetailController {
    @Autowired
    private ReceivingDetailService receivingDetailService;

    @GetMapping(path="/receivedetail")
    public List<ReceivingDetail> getAllReceivingDetails(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return receivingDetailService.getAllReceivingDetails(thePage,pageable);
    }

    @GetMapping(path="/receivedetail/{id}")
    public ReceivingDetail getReceivingDetailByID(@PathVariable Long id){
        return receivingDetailService.getReceivingDetailByID(id);
    }

    @PostMapping(path="/receivedetail")
    public ReceivingDetail addReceivingDetail(@RequestBody ReceivingDetail receivingDetail){
        return receivingDetailService.addReceivingDetail(receivingDetail);
    }

    @DeleteMapping(path="/receivedetail/{id}")
    public ReceivingDetail deleteReceivingDetail(@PathVariable Long id){
        return receivingDetailService.deleteReceivingDetail(id);
    }

    @PutMapping(path="/receivedetail")
    public ReceivingDetail updateReceivingDetail(@RequestBody ReceivingDetail receivingDetail){
        return receivingDetailService.updateReceivingDetail(receivingDetail);
    }
}
