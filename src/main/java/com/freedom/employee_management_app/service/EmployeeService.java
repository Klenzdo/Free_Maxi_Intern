package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.dto.LoginRequestDto;
import com.freedom.employee_management_app.dto.LoginResponse;
import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.payload.response.LogOutResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface EmployeeService {
    ApiResponse<LoginResponse> login(LoginRequestDto request);
    ApiResponse<String> logout(String authHeader);
}
