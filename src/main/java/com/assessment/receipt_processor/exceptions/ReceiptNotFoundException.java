package com.assessment.receipt_processor.exceptions;

public class ReceiptNotFoundException extends RuntimeException{
    public ReceiptNotFoundException(String message) {
        super(message);
    }
}
