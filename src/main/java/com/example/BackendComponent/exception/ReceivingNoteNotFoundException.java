package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ReceivingNoteNotFoundException extends RuntimeException{
    public ReceivingNoteNotFoundException(Long id){
        super(MessageFormat.format("Could not find receiving note with id ", id));
    }
}
