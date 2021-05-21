package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ProviderAlreadyExistException extends RuntimeException{
    public ProviderAlreadyExistException(Long id){
        super(MessageFormat.format("Provider with id {0} already exists!", id));
    }
}
