package com.academy.demo.exception;

public class CustomSQLException extends Exception {

    private String message;

    public CustomSQLException(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
