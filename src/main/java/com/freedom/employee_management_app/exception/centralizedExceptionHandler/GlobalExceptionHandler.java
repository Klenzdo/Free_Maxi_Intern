package com.freedom.employee_management_app.exception.centralizedExceptionHandler;

import com.freedom.employee_management_app.exception.DuplicateMerchandiseException;
import com.freedom.employee_management_app.exception.EmployeeIdNotFoundException;
import com.freedom.employee_management_app.exception.UnauthorizedException;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateMerchandiseException.class)
    public ResponseEntity<ApiResponse<String>> handleDuplicateCropException(DuplicateMerchandiseException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<String>> handleUnauthorizedException(UnauthorizedException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(EmployeeIdNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEmployeeIdNotFoundException(EmployeeIdNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiResponse<String> response = new ApiResponse<>(ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
