package com.example.BackendComponent.service;

import com.example.BackendComponent.entity.*;
import com.example.BackendComponent.exception.InventoryDeliveryNoteNotFoundException;
import com.example.BackendComponent.repository.InventoryDeliveryNoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class InventoryDeliveryNoteService {
    private final InventoryDeliveryNoteRepository inventoryDeliveryNoteRepository;

    public InventoryDeliveryNoteService(InventoryDeliveryNoteRepository inventoryDeliveryNoteRepository) {
        this.inventoryDeliveryNoteRepository = inventoryDeliveryNoteRepository;
    }

    public List<InventoryDeliveryNote> getAllInventoryDeliveryNotes() {
        return StreamSupport
                .stream(inventoryDeliveryNoteRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public InventoryDeliveryNote getInventoryDeliveryNoteByID(Long id) {
        return inventoryDeliveryNoteRepository.findById(id).orElseThrow(() -> new InventoryDeliveryNoteNotFoundException(id));
    }

    public InventoryDeliveryNote addInventoryDeliveryNote(InventoryDeliveryNote inventoryDeliveryNote) {
        return inventoryDeliveryNoteRepository.save(inventoryDeliveryNote);
    }

    public InventoryDeliveryNote deleteInventoryDeliveryNote(Long id) {
        InventoryDeliveryNote inventoryDeliveryNoteToDelete = getInventoryDeliveryNoteByID(id);
        inventoryDeliveryNoteRepository.delete(inventoryDeliveryNoteToDelete);
        return inventoryDeliveryNoteToDelete;
    }

    public InventoryDeliveryNote updateInventoryDeliveryNote(Long id, InventoryDeliveryNote newInventoryDeliveryNote) {
        InventoryDeliveryNote inventoryDeliveryNoteToUpdate = getInventoryDeliveryNoteByID(id);
        inventoryDeliveryNoteToUpdate.setInventoryDeliveryNoteDate(newInventoryDeliveryNote.getInventoryDeliveryNoteDate());
        inventoryDeliveryNoteToUpdate.setInventoryDeliveryNoteStaff(newInventoryDeliveryNote.getInventoryDeliveryNoteStaff());
        return inventoryDeliveryNoteToUpdate;
    }

    public String getInventoryDeliveryNoteDetails(Long inventoryDeliveryNoteID) {
        InventoryDeliveryNote inventoryDeliveryNote = getInventoryDeliveryNoteByID(inventoryDeliveryNoteID);
        return "Inventory Delivery Note ID: { " + inventoryDeliveryNote.getInventoryDeliveryNoteId() + " }" + "\n" + "Date: {" + inventoryDeliveryNote.getInventoryDeliveryNoteDate() + "}" + "\n" + "Staff Info: {" + inventoryDeliveryNote.getInventoryDeliveryNoteStaff();
    }

}
