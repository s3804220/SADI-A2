package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class DeliveryDetailAlreadyExistException extends RuntimeException{
    public DeliveryDetailAlreadyExistException(Long id){
        super(MessageFormat.format("Delivery detail with id {0} already exists!", id));
    }
}
