package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.dto.AuthResponse;
import com.freedom.employee_management_app.dto.LoginRequestDto;
import com.freedom.employee_management_app.dto.LoginResponse;
import com.freedom.employee_management_app.dto.RegistrationInfo;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.request.RegistrationRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.service.AdminService;
import com.freedom.employee_management_app.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/employee")
//@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login (@RequestBody LoginRequestDto request){
        return ResponseEntity.ok (employeeService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout (@RequestHeader(value ="Authorization", required = false)  String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Invalid or missing Authorization header", null));
        }
//        if (token.startsWith("Bearer")){
//            token = token.substring(7);
//        }
        String token = authHeader.substring(7);
        ApiResponse<String> response = employeeService.logout(token);
        return ResponseEntity.ok(response);
    }
}
