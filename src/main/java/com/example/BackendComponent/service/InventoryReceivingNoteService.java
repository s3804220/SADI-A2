package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.InventoryReceivingNoteNotFoundException;
import com.example.BackendComponent.repository.InventoryReceivingNoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class InventoryReceivingNoteService {
    private final InventoryReceivingNoteRepository inventoryReceivingNoteRepository;

    public InventoryReceivingNoteService(InventoryReceivingNoteRepository inventoryReceivingNoteRepository) {
        this.inventoryReceivingNoteRepository = inventoryReceivingNoteRepository;
    }

    public List<InventoryReceivingNote> getAllInventoryReceivingNotes() {
        return StreamSupport
                .stream(inventoryReceivingNoteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
