package com.freedom.employee_management_app.exception;

public  class InvalidPasswordException extends Exception {
    public InvalidPasswordException (String message) {
        super(message);
    }
}
