package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.dto.ForgotPasswordRequest;
import com.freedom.employee_management_app.dto.ResetPasswordRequest;
import com.freedom.employee_management_app.exception.EmployeeNotFoundException;
import com.freedom.employee_management_app.exception.InvalidPasswordException;
import com.freedom.employee_management_app.exception.InvalidTokenException;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController{
    private final EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequest request) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.forgotPassword(request));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request) throws InvalidTokenException{
        return ResponseEntity.ok(employeeService.resetPassword(request));
    }
}
