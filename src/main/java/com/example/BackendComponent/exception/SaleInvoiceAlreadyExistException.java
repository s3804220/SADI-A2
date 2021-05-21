package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class SaleInvoiceAlreadyExistException extends RuntimeException{
    public SaleInvoiceAlreadyExistException(Long id){
        super(MessageFormat.format("Sale invoice with id {0} already exists!", id));
    }
}
