package com.freedom.employee_management_app.dto;


public class LoginRequestDto {
    private String employeeId;
    private String password;


    public LoginRequestDto(String employeeId, String password) {
        this.employeeId = employeeId;
        this.password = password;
    }

    public LoginRequestDto() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
