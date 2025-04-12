package com.freedom.employee_management_app.exception;

public class CloudinaryException extends RuntimeException{
    public CloudinaryException(String message, Throwable cause){
        super(message, cause);
    }

    public CloudinaryException(String message){
        super(message);
    }
}
