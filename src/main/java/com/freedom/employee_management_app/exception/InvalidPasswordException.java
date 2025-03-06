package com.freedom.employee_management_app.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException (String message) {
        super(message);
    }
}
