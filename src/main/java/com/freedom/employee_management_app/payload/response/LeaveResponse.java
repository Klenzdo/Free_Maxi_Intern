package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
public class LeaveResponse {
    private LeaveType type;

    private LocalDate startDate;
    private LocalDate endDate;
    private String status = ""; // check later after implementation
    private String documentUrl;
//    private Employee employee;

    public LeaveResponse(LeaveType type,  LocalDate startDate,LocalDate endDate,String documentUrl, String status) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.documentUrl = documentUrl;
        this.status = status;
//        this.employee = employee;

    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }
}
