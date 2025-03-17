package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.dto.*;
import com.freedom.employee_management_app.payload.request.LoginRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.EmployeeResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.service.AdminService;
import com.freedom.employee_management_app.service.EmployeeService;
import com.freedom.employee_management_app.service.LeaveService;
import org.springframework.data.domain.Page;
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
    private final EmployeeService employeeService;
    private final LeaveService leaveService;

    public AdminController(AdminService adminService, EmployeeService employeeService, LeaveService leaveService) {
        this.adminService = adminService;
        this.employeeService = employeeService;
        this.leaveService = leaveService;

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
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/pending-leaves")
    public ResponseEntity<ApiResponse<Page<LeaveResponse>>> getPendingLeaveResponse(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<Page<LeaveResponse>> response = leaveService.getPendingLeaveRequests(page, size);
        return ResponseEntity.ok(response);

    }
}
