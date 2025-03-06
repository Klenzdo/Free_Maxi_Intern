package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.entity.ClockLog;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.request.ClockLogRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.ClockInResponse;
import com.freedom.employee_management_app.payload.response.ClockLogResponse;
import com.freedom.employee_management_app.payload.response.ClockOutResponse;
import com.freedom.employee_management_app.repository.ClockLogRepository;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.service.ClockService;
import com.freedom.employee_management_app.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

@Service

public class ClockServiceImpl implements ClockService {

    private final ClockLogRepository clockLogRepository;

    public ClockServiceImpl(ClockLogRepository clockLogRepository, EmployeeRepository employeeRepository, SecurityUtils securityUtils) {
        this.clockLogRepository = clockLogRepository;
        this.employeeRepository = employeeRepository;
        this.securityUtils = securityUtils;
    }

    private final EmployeeRepository employeeRepository;
    private final SecurityUtils securityUtils;

    @Override
    public ApiResponse<ClockInResponse> clockIn(Employee employee) {

       if (employee.isLocked()){
            throw new IllegalStateException("Employee is locked due to excessive late arrivals");
        }
        LocalDateTime now = LocalDateTime.now();

        // Check if employee has already clocked in today
        LocalDateTime startOfDay = now.with(LocalTime.MIN);
        LocalDateTime endOfDay = now.with(LocalTime.MAX);

        ClockLog existingLog = clockLogRepository.findClockLogByEmployeeIdAndClockInTimeBetween(
                employee.getId(), startOfDay, endOfDay);

        if (existingLog !=null){
            throw new IllegalStateException("Employee has already clocked in today");
        }
//        boolean isLate = now.isAfter(LocalDateTime.of (8,0));
        LocalDateTime lateThreshold = now.with(LocalTime.of(8,0));

        boolean isLate = now.isAfter(lateThreshold);


        ClockLog log = new ClockLog();
//        log.setEmployee(employeeRepository.findById(employeeId).orElseThrow());
        log.setEmployee(employee);
        log.setClockInTime(now);
        log.setLate(isLate);
        clockLogRepository.save(log);

        if(isLate) {
            incrementLateCount(employee.getId());// employeeId
        }

        return new ApiResponse<>("Successfully clocked in", new ClockInResponse(log));

    }


    @Override
    public ApiResponse<ClockOutResponse> clockOut(Long employeeId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.with(LocalTime.of(6,0));
        ClockLog log = clockLogRepository.findClockLogByEmployeeIdAndClockInTimeBetween(employeeId, from, now);
        log.setClockOutTime(LocalDateTime.now());
        clockLogRepository.save(log);

        return new ApiResponse<>("Successfully clocked out", new ClockOutResponse(log));

    }


     @Override
    public ApiResponse<List<ClockLogResponse>> getClockLogs(Long employeeId, LocalDate startDate, LocalDate endDate, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("clockInTime").descending());
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        Page<ClockLog> logs = clockLogRepository.findClockLogsByEmployeeAndDateRange(
                employeeId, startDateTime, endDateTime, pageable);
         List<ClockLogResponse> clockLog = logs.getContent().stream().map(ClockLogResponse::new).toList();



        return new ApiResponse<>("Successfull", clockLog, logs.isFirst(), logs.isLast(), logs.getNumber(), logs.getSize(), logs.getTotalPages());
    }

    private void incrementLateCount(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setLateCount(employee.getLateCount() + 1);

        //Lock employee account after 3 late arrivals
        if(employee.getLateCount() >= 3)
                employee.setLocked(true);

            employeeRepository.save(employee);

    }


}

