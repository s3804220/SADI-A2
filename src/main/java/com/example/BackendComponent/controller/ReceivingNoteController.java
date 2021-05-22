package com.example.BackendComponent.controller;

import com.example.BackendComponent.entity.Order;
import com.example.BackendComponent.entity.Product;
import com.example.BackendComponent.entity.ReceivingNote;
import com.example.BackendComponent.service.ReceivingNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(path="/receive/search")
    public List<ReceivingNote> searchReceivingNoteBy(@RequestParam Optional<String> start, @RequestParam Optional<String> end){
        LocalDate fromdate, todate;
        fromdate = start.map(LocalDate::parse).orElse(null);
        todate = end.map(LocalDate::parse).orElse(null);
        return receivingNoteService.searchReceivingNoteBy(fromdate, todate);
    }
}
