package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class OrderDetailAlreadyExistException extends RuntimeException{
    public OrderDetailAlreadyExistException(Long id){
        super(MessageFormat.format("Order detail with id {0} already exists!", id));
    }
}
