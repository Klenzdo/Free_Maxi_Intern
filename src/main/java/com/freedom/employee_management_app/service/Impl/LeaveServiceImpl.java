package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.dto.EmailDetails;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.Leave;
import com.freedom.employee_management_app.enums.LeaveStatus;
import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import com.freedom.employee_management_app.repository.LeaveRepository;
import com.freedom.employee_management_app.service.LeaveService;
import com.freedom.employee_management_app.service.NotificationService;
import com.freedom.employee_management_app.utils.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service

public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final SecurityUtils securityUtils;
    private final NotificationService notificationService;

    public LeaveServiceImpl(LeaveRepository leaveRepository, SecurityUtils securityUtils, NotificationService notificationService) {
        this.leaveRepository = leaveRepository;
        this.securityUtils = securityUtils;
        this.notificationService = notificationService;
    }

    public ApiResponse<LeaveResponse> applyForLeave(LeaveRequest request){
        Employee employee = securityUtils.getCurrentEmployee();

        Leave leave = new Leave();

        leave.setEmployee(employee);
        leave.setType(request.getType());
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());
        leave.setStatus(request.getStatus());
        leave.setDocumentUrl(request.getDocumentUrl());

        leaveRepository.save(leave);

        LeaveResponse response = new LeaveResponse(
                leave.getId(),
                employee.getEmployeeId(),
                leave.getType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getDocumentUrl()

        );
        return new ApiResponse<>("Leave request submitted successfully", response);

    }

//    @Override
//    public ApiResponse<Page<LeaveResponse>> getPendingLeaveRequests(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Leave> leavePage = leaveRepository.findByStatus("PENDING", pageable);
//        Page<LeaveResponse> leaveResponsePage = leavePage.map(leave ->
//                new LeaveResponse(leave.getId(), leave.getEmployee().getEmployeeId(), leave.getType(), leave.getStartDate(), leave.getEndDate(), leave.getDocumentUrl()));
//
//        return new ApiResponse<>(
//                "Pending leave requests retrieved successfully",
//                leaveResponsePage,
//                leaveResponsePage.isFirst(),
//                leaveResponsePage.isLast(),
//                leaveResponsePage.getNumber(),
//                leaveResponsePage.getSize(),
//                (int) leaveResponsePage.getTotalElements()
//        );
//    }

    @Override
    public Page<LeaveResponse> getLeaveByStatus(String status, Pageable pageable) {
        Page<Leave> leavePage = leaveRepository.findByStatus(status, pageable);
        return leavePage.map(leave -> new LeaveResponse(
                leave.getId(),
                leave.getEmployee().getEmployeeId(),
                leave.getType(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getDocumentUrl()
        ));
    }

    @Override
    public void sendLeaveSummaryToAdmin(Page<LeaveResponse> pending, Page<LeaveResponse> approved, Page<LeaveResponse> rejected) {

        String emailSubject = "Leave Status Summary";

        String emailBody = String.format(
                "Leave Summary: \n\n" +
                        "Pending Requests: %d\n" +
                        "Approved Requests: %d\n" +
                        "Rejected Requests: %d\n" +
                        "Check the system for more details.",
                pending.getTotalElements(),
                approved.getTotalElements(),
                rejected.getTotalElements()
        );

        EmailDetails emailDetails = new EmailDetails("freedoklenz@gmail.com", emailBody, emailSubject);


        notificationService.sendEmailNotification(emailDetails);

        notificationService.sendInternalNotification("Check your email for leave summary");
    }


    @Override
    public ApiResponse<Map<String, Page<LeaveResponse>>> getAllLeaveRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<LeaveResponse> pendingLeaves = getLeaveByStatus("PENDING", pageable);
        Page<LeaveResponse> approvedLeaves = getLeaveByStatus("APPROVED", pageable);
        Page<LeaveResponse> rejectedLeaves = getLeaveByStatus("REJECTED", pageable);

        Map<String, Page<LeaveResponse>> categorizedLeaves = new HashMap<>();
        categorizedLeaves.put("PENDING", pendingLeaves);
        categorizedLeaves.put("APPROVED", approvedLeaves);
        categorizedLeaves.put("REJECTED", rejectedLeaves);

        sendLeaveSummaryToAdmin(pendingLeaves, approvedLeaves, rejectedLeaves);

        return new ApiResponse<>("Leave status retrieved successfully", categorizedLeaves);
    }


}



