package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ReceivingDetailAlreadyExistException extends RuntimeException{
    public ReceivingDetailAlreadyExistException(Long id){
        super(MessageFormat.format("Receiving detail with id {0} already exists!", id));
    }
}
