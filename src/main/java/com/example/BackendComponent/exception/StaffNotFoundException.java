package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class StaffNotFoundException extends RuntimeException{
    public StaffNotFoundException(Long id){
        super(MessageFormat.format("Could not find staff with id ", id));
    }
}
