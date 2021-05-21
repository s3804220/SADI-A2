package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class StaffAlreadyExistException extends RuntimeException{
    public StaffAlreadyExistException(Long id){
        super(MessageFormat.format("Staff with id {0} already exists!", id));
    }
}
