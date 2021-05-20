package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class InventoryReceivingNoteNotFoundException extends RuntimeException {
    public InventoryReceivingNoteNotFoundException(Long id){
        super(MessageFormat.format("Could not find an inventory receiving note with id ", id));
    }
}
