package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class DeliveryNoteAlreadyExistException extends RuntimeException{
    public DeliveryNoteAlreadyExistException(Long id){
        super(MessageFormat.format("Delivery note with id {0} already exists!", id));
    }
}
