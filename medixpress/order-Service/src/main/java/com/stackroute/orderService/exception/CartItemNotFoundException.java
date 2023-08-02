package com.stackroute.orderService.exception;

public class CartItemNotFoundException extends RuntimeException{
    private String message;

    public CartItemNotFoundException(String message)
    {
        super();
        this.message=message;
    }

    public String getMessage() {

        return message;
    }
}
