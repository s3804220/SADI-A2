package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class SaleDetailNotFoundException extends RuntimeException{
    public SaleDetailNotFoundException(Long id){
        super(MessageFormat.format("Could not find sale detail with id ", id));
    }
}
