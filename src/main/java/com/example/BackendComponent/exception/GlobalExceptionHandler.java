package com.example.BackendComponent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ResponseEntity<>("Could not find category with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCategoryAlreadyExistException(CategoryAlreadyExistException e) {
        return new ResponseEntity<>("A category with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return new ResponseEntity<>("Could not find customer with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCustomerAlreadyExistException(CustomerAlreadyExistException e) {
        return new ResponseEntity<>("A customer with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>("Could not find order with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleOrderAlreadyExistException(OrderAlreadyExistException e) {
        return new ResponseEntity<>("An order with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>("Could not find product with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProductAlreadyExistException(ProductAlreadyExistException e) {
        return new ResponseEntity<>("A product with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProviderNotFoundException(ProviderNotFoundException e) {
        return new ResponseEntity<>("Could not find provider with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProviderAlreadyExistException(ProviderAlreadyExistException e) {
        return new ResponseEntity<>("A provider with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleReceivingNoteNotFoundException(ReceivingNoteNotFoundException e) {
        return new ResponseEntity<>("Could not find receiving note with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleReceivingNoteAlreadyExistException(ReceivingNoteAlreadyExistException e) {
        return new ResponseEntity<>("A receiving note with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDeliveryNoteNotFoundException(DeliveryNoteNotFoundException e) {
        return new ResponseEntity<>("Could not find delivery note with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDeliveryNoteAlreadyExistException(DeliveryNoteAlreadyExistException e) {
        return new ResponseEntity<>("A delivery note with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleSaleInvoiceNotFoundException(SaleInvoiceNotFoundException e) {
        return new ResponseEntity<>("Could not find sale invoice with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleSaleInvoiceAlreadyExistException(SaleInvoiceAlreadyExistException e) {
        return new ResponseEntity<>("A sale invoice with that ID already exists!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleStaffNotFoundException(StaffNotFoundException e) {
        return new ResponseEntity<>("Could not find staff with that ID!", HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleStaffAlreadyExistException(StaffAlreadyExistException e) {
        return new ResponseEntity<>("A staff with that ID already exists!", HttpStatus.OK);
    }
}
