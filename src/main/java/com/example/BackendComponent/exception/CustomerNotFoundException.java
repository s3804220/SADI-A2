package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super(MessageFormat.format("Could not find customer with id ", id));
    }
}
