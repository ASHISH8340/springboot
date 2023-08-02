package com.stackroute.exception;

public class PaymentNotFoundException extends RuntimeException{
    private String message;

    public String getMessage() {
        return message;
    }

    public PaymentNotFoundException(String message) {
        super();
        this.message = message;
    }
}
