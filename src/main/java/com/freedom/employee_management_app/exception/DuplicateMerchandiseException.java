package com.freedom.employee_management_app.exception;

public class DuplicateMerchandiseException extends RuntimeException{
    public DuplicateMerchandiseException(String message){
        super(message);
    }
}
