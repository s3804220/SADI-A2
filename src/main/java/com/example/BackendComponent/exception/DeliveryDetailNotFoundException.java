package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class DeliveryDetailNotFoundException extends RuntimeException{
    public DeliveryDetailNotFoundException(Long id){
        super(MessageFormat.format("Could not find delivery detail with id ", id));
    }
}
