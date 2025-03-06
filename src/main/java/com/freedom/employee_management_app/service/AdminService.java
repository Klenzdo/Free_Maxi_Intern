package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.dto.*;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.request.LoginRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LogOutResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AdminService {

    CreateEmployeeDto createEmployee(EmployeeInfo employeeInfo);
    ApiResponse<LoginResponse> login(LoginRequest request);
    ApiResponse<String> updateLeaveStatus(Long leaveId, String status);
    List<Employee> getAllEmployees();
//    ApiResponse<LogOutResponse> logout(HttpServletRequest request);




}




// Method parameters shd be in a class and be called
// Api  response on line 10 here
// create a constructor for the creation of employee in employee entity to handle the line 44 to 49 in adminserviceimpl