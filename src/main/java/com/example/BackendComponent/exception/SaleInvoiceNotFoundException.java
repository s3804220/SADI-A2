package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class SaleInvoiceNotFoundException extends RuntimeException{
    public SaleInvoiceNotFoundException(Long id){
        super(MessageFormat.format("Could not find sale invoice with id ", id));
    }
}
