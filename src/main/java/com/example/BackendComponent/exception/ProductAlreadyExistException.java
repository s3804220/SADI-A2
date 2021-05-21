package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(Long id){
        super(MessageFormat.format("Product with id {0} already exists!", id));
    }
}
