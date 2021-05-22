package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(Long id){
        super(MessageFormat.format("Could not find order detail with id ", id));
    }
}
