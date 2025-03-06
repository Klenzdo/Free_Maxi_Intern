package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.dto.*;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.request.EmployeeRequest;
import com.freedom.employee_management_app.payload.request.LoginRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.EmployeeResponse;
import com.freedom.employee_management_app.service.AdminService;
import com.freedom.employee_management_app.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping(value ="/create-employee",  consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CreateEmployeeDto>> createEmployee(@RequestBody EmployeeInfo employeeInfo) {
        CreateEmployeeDto createdEmployee = adminService.createEmployee(employeeInfo);
        ApiResponse<CreateEmployeeDto> response = new ApiResponse<>("Employee created successfully", createdEmployee);

        return ResponseEntity.ok(response);

    }
    
    @PostMapping("/login")
public ResponseEntity<ApiResponse<LoginResponse>> login (@RequestBody LoginRequest request){
        System.out.println("Login method entered");
      return ResponseEntity.ok (adminService.login(request));


    }
    @PostMapping("/update-leave-status/{leaveId}")
    public ResponseEntity<ApiResponse<String>> updateLeaveStatus(@PathVariable Long leaveId,
                                                                 @RequestParam String status){
        return ResponseEntity.ok(adminService.updateLeaveStatus(leaveId, status));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(adminService.getAllEmployees());
    }

}
