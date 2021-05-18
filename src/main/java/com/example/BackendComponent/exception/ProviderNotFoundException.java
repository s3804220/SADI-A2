package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ProviderNotFoundException extends RuntimeException{
    public ProviderNotFoundException(Long id){
        super(MessageFormat.format("Could not find provider with id ", id));
    }
}
