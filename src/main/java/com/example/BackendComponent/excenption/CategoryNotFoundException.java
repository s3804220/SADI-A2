package com.example.BackendComponent.excenption;

import java.text.MessageFormat;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id){
        super(MessageFormat.format("Could not find category with id ", id));

    }
}
