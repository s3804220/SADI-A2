package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class SaleDetailAlreadyExistException extends RuntimeException{
    public SaleDetailAlreadyExistException(Long id){
        super(MessageFormat.format("Sale detail with id {0} already exists!", id));
    }
}
