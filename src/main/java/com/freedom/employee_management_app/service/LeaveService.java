package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.enums.LeaveStatus;
import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface LeaveService {
    ApiResponse<LeaveResponse> applyForLeave(LeaveRequest request);
//    ApiResponse<Page<LeaveResponse>> getPendingLeaveRequests(int page, int size);
    ApiResponse<Map<String, Page<LeaveResponse>>> getAllLeaveRequests(int page, int size);
     Page<LeaveResponse> getLeaveByStatus(String status, Pageable pageable);
    void  sendLeaveSummaryToAdmin(Page<LeaveResponse> pending, Page<LeaveResponse> approved, Page<LeaveResponse> rejected);

}
