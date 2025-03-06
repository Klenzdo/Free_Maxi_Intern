package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/leaves")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<ApiResponse<LeaveResponse>> applyForLeave (
            @RequestBody LeaveRequest request){
        return ResponseEntity.ok(leaveService.applyForLeave(request));
    }

}
