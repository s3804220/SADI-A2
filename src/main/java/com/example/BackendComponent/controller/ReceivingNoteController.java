package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.service.ReceivingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ReceivingNoteController {
    @Autowired
    private ReceivingNoteService receivingNoteService;

    @GetMapping(path="/receive")
    public List<ReceivingNote> getAllReceivingNotes(){
        return receivingNoteService.getAllReceivingNotes();
    }

    @GetMapping(path="/receive/{id}")
    public ReceivingNote getReceivingNoteById(@PathVariable Long id){
        return receivingNoteService.getReceivingNoteByID(id);
    }

    @PostMapping(path="/receive")
    public ReceivingNote addReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.addReceivingNote(receivingNote);
    }

    @DeleteMapping(path="/receive/{id}")
    public ReceivingNote deleteReceivingNote(@PathVariable Long id){
        return receivingNoteService.deleteReceivingNote(id);
    }

    @PutMapping(path="/receive")
    public ReceivingNote updateReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.updateReceivingNote(receivingNote);
    }
}
