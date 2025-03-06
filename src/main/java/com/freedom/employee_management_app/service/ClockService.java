package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.entity.ClockLog;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.request.ClockLogRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.ClockInResponse;
import com.freedom.employee_management_app.payload.response.ClockLogResponse;
import com.freedom.employee_management_app.payload.response.ClockOutResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ClockService {
    ApiResponse<ClockInResponse> clockIn(Employee employee); // I initially passed Long employeeId
//    void clockIn (Long employeeId);
    ApiResponse<ClockOutResponse> clockOut(Long employeeId);
//    void clockOut(Long employeeId);

    ApiResponse<List<ClockLogResponse>> getClockLogs(Long employeeId, LocalDate startDate,
                                                     LocalDate endDate, int page, int size);




}
