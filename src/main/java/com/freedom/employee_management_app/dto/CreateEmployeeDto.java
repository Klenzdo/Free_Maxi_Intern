package com.freedom.employee_management_app.dto;

import com.freedom.employee_management_app.entity.Employee;

public class CreateEmployeeDto {


    private String employeeId;
    private String email;


    public CreateEmployeeDto(Employee employee){
        this.employeeId = employee.getEmployeeId();
        this.email = employee.getEmail();


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
}

