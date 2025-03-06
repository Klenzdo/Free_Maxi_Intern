package com.freedom.employee_management_app.payload.request;

import com.freedom.employee_management_app.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
public class LeaveRequest {
    private LeaveType type;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status = "PENDING";

    private String documentUrl;

    public LeaveRequest(LeaveType type, LocalDate startDate, LocalDate endDate, String status, String documentUrl) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.documentUrl = documentUrl;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
}
