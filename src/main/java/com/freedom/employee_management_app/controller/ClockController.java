package com.freedom.employee_management_app.controller;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.ClockInResponse;
import com.freedom.employee_management_app.payload.response.ClockLogResponse;
import com.freedom.employee_management_app.payload.response.ClockOutResponse;
import com.freedom.employee_management_app.service.ClockService;
import com.freedom.employee_management_app.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v3/clock")
public class ClockController {

    private final ClockService clockService;
    private final SecurityUtils securityUtils;

    public ClockController(ClockService clockService, SecurityUtils securityUtils){
        this.clockService = clockService;
        this.securityUtils = securityUtils;
    }

    @PostMapping("/in")
    public ResponseEntity<ApiResponse<ClockInResponse>> clockIn (){
        try {
            Employee employee = securityUtils.getCurrentEmployee();
            ApiResponse<ClockInResponse>  response = clockService.clockIn(employee);
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ex.getMessage(), null)); // Handle exception
        }catch (Exception ex){ // Catch other potential exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("An error occurred", null));
        }
    }

    @PostMapping("/out")
    public ResponseEntity<ApiResponse<ClockOutResponse>>  clockOut(){
        try{
            Employee employee = securityUtils.getCurrentEmployee();
            ApiResponse<ClockOutResponse> response = clockService.clockOut(employee.getId());
            return ResponseEntity.ok(response);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ex.getMessage(), null));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>("An error occurred", null));
        }
    }

    @GetMapping ("/records")
    public ResponseEntity<ApiResponse<List<ClockLogResponse>>> getClockLogs(

            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
            ){
        System.out.println(startDate);
        System.out.println(endDate);
        Employee employee = securityUtils.getCurrentEmployee();
        ApiResponse<List<ClockLogResponse>>response = clockService.getClockLogs(employee.getId(), LocalDate.parse(startDate), LocalDate.parse(endDate), page, size);
        return ResponseEntity.ok(response);
    }
}
