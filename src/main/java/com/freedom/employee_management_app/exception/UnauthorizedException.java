package com.freedom.employee_management_app.exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException (String message) {
        super(message);
    }
}
