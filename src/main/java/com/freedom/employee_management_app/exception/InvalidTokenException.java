package com.freedom.employee_management_app.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message)
    {
        super(message);
    }
}


