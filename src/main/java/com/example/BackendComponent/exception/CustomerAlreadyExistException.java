package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(Long id){
        super(MessageFormat.format("Customer with id {0} already exists!", id));
    }
}
