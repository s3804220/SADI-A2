package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ReceivingNoteAlreadyExistException extends RuntimeException{
    public ReceivingNoteAlreadyExistException(Long id){
        super(MessageFormat.format("Receiving note with id {0} already exists!", id));
    }
}
