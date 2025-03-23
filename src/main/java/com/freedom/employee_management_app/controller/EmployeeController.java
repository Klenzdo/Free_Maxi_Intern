package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.dto.*;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.exception.EmployeeNotFoundException;
import com.freedom.employee_management_app.exception.InvalidPasswordException;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.service.AdminService;
import com.freedom.employee_management_app.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login (@RequestBody LoginRequestDto request) throws Exception{
        return ResponseEntity.ok (employeeService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout (@RequestHeader(value ="Authorization", required = false)  String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Invalid or missing Authorization header", null));
        }
        String token = authHeader.substring(7);
        ApiResponse<String> response = employeeService.logout(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("update-password")
    public  ResponseEntity<ApiResponse<String>> updatePassword(@RequestBody updatePasswordRequest request) throws InvalidPasswordException, EmployeeNotFoundException {
        ApiResponse<String> response = employeeService.updatePassword(request.getNewPassword(), request.getOldPassword());
        return ResponseEntity.ok(response);

    }
//
//    @PostMapping("/notify-admin/{employeeId}")
//    public ResponseEntity<ApiResponse<Employee>> notifyAdminAboutLockedEmployee (@PathVariable Long employeeId){
//        try{
//            Employee employee = .findByEmployeeId(employeeId)
//        }
//    }
}
