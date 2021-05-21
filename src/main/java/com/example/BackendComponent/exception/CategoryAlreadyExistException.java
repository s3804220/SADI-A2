package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class CategoryAlreadyExistException extends RuntimeException{
    public CategoryAlreadyExistException(Long id){
        super(MessageFormat.format("Category with id {0} already exists!", id));
    }
}
