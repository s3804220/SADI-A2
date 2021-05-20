package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class InventoryDeliveryNoteNotFoundException extends RuntimeException {
    public InventoryDeliveryNoteNotFoundException(Long id){
        super(MessageFormat.format("Could not find an inventory delivery note with id ", id));
    }
}
