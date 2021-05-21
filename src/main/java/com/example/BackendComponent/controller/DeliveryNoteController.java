package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.DeliveryNote;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.service.DeliveryNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class DeliveryNoteController {
    @Autowired
    private DeliveryNoteService deliveryNoteService;

    @GetMapping(path="/delivery")
    public List<DeliveryNote> getAllDeliveryNotes(){
        return deliveryNoteService.getAllDeliveryNotes();
    }

    @GetMapping(path="/delivery/{id}")
    public DeliveryNote getDeliveryNoteById(@PathVariable Long id){
        return deliveryNoteService.getDeliveryNoteByID(id);
    }

    @PostMapping(path="/delivery")
    public DeliveryNote addDeliveryNote(@RequestBody DeliveryNote deliveryNote){
        return deliveryNoteService.addDeliveryNote(deliveryNote);
    }

    @DeleteMapping(path="/delivery/{id}")
    public DeliveryNote deleteDeliveryNote(@PathVariable Long id){
        return deliveryNoteService.deleteDeliveryNote(id);
    }

    @PutMapping(path="/delivery")
    public DeliveryNote updateDeliveryNote(@RequestBody DeliveryNote deliveryNote){
        return deliveryNoteService.updateDeliveryNote(deliveryNote);
    }
}
