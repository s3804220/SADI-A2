package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super(MessageFormat.format("Could not find product with id ", id));
    }
}
