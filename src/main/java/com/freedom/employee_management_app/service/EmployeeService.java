package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.dto.LoginRequestDto;
import com.freedom.employee_management_app.dto.LoginResponse;
import com.freedom.employee_management_app.exception.EmployeeNotFoundException;
import com.freedom.employee_management_app.exception.InvalidPasswordException;
import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.EmployeeResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.payload.response.LogOutResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    ApiResponse<LoginResponse> login(LoginRequestDto request) throws Exception;
    ApiResponse<String> logout(String authHeader);
    ApiResponse<String> updatePassword( String newPassword, String oldPassword) throws EmployeeNotFoundException, InvalidPasswordException;
    List<EmployeeResponse> getAllEmployees();


}
