package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class OrderAlreadyExistException extends RuntimeException{
    public OrderAlreadyExistException(Long id){
        super(MessageFormat.format("Order with id {0} already exists!", id));
    }
}
