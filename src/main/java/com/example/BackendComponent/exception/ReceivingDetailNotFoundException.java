package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ReceivingDetailNotFoundException extends RuntimeException{
    public ReceivingDetailNotFoundException(Long id){
        super(MessageFormat.format("Could not find receiving detail with id ", id));
    }
}
