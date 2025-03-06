package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.Leave;
import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.repository.LeaveRepository;
import com.freedom.employee_management_app.service.LeaveService;
import com.freedom.employee_management_app.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service

public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final SecurityUtils securityUtils;

    public LeaveServiceImpl(LeaveRepository leaveRepository, SecurityUtils securityUtils) {
        this.leaveRepository = leaveRepository;
        this.securityUtils = securityUtils;
    }

    public ApiResponse<LeaveResponse> applyForLeave(LeaveRequest request){
        Employee employee = securityUtils.getCurrentEmployee();

        Leave leave = new Leave();

        leave.setEmployee(employee);
        leave.setType(request.getType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setStatus("PENDING");
        leave.setDocumentUrl(request.getDocumentUrl());

        leaveRepository.save(leave);

        LeaveResponse response = new LeaveResponse(
                leave.getType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getStatus(),
                leave.getDocumentUrl()
//                leave.getEmployee()
        );
        return new ApiResponse<>("Leave request submitted successfully", response);

    }

    }



