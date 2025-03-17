package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public class EmployeeResponse {

    private String fullName;
    private String employeeId;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles role;

    public EmployeeResponse(String fullName, String employeeId, String email, Roles role) {
        this.fullName = fullName;
        this.employeeId = employeeId;
        this.email = email;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
