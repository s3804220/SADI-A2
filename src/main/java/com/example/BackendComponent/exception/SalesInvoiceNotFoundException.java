package com.example.BackendComponent.exception;

import java.text.MessageFormat;

public class SalesInvoiceNotFoundException extends RuntimeException {
    public SalesInvoiceNotFoundException(Long id){
        super(MessageFormat.format("Could not find an inventory sales invoice with id ", id));
    }
}
