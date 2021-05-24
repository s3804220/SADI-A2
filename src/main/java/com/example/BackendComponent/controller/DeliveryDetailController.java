package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.DeliveryDetail;
import com.example.BackendComponent.entity.ReceivingDetail;
import com.example.BackendComponent.service.DeliveryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeliveryDetailController {
    @Autowired
    private DeliveryDetailService deliveryDetailService;

    @GetMapping(path="/deliverydetail")
    public List<DeliveryDetail> getAllDeliveryDetails(@RequestParam Optional<Integer> page){
        boolean pageable;
        int thePage = 0;
        pageable = page.isPresent();
        if(page.isPresent()){
            thePage = page.get();
        }
        return deliveryDetailService.getAllDeliveryDetails(thePage, pageable);
    }

    @GetMapping(path="/deliverydetail/{id}")
    public DeliveryDetail getDeliveryDetailByID(@PathVariable Long id){
        return deliveryDetailService.getDeliveryDetailByID(id);
    }

    @PostMapping(path="/deliverydetail")
    public DeliveryDetail addDeliveryDetail(@RequestBody DeliveryDetail deliveryDetail){
        return deliveryDetailService.addDeliveryDetail(deliveryDetail);
    }

    @DeleteMapping(path="/deliverydetail/{id}")
    public DeliveryDetail deleteDeliveryDetail(@PathVariable Long id){
        return deliveryDetailService.deleteDeliveryDetail(id);
    }

    @PutMapping(path="/deliverydetail")
    public DeliveryDetail updateDeliveryDetail(@RequestBody DeliveryDetail deliveryDetail){
        return deliveryDetailService.updateDeliveryDetail(deliveryDetail);
    }
}
