package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.payload.request.LeaveRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.LeaveResponse;
import org.springframework.data.domain.Page;

public interface LeaveService {
    ApiResponse<LeaveResponse> applyForLeave(LeaveRequest request);
    ApiResponse<Page<LeaveResponse>> getPendingLeaveRequests(int page, int size);

}
