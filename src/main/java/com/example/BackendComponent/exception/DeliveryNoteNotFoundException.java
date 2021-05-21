package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class DeliveryNoteNotFoundException extends RuntimeException{
    public DeliveryNoteNotFoundException(Long id){
        super(MessageFormat.format("Could not find delivery note with id ", id));
    }
}
