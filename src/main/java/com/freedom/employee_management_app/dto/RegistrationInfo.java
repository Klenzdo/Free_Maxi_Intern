package com.freedom.employee_management_app.dto;

import com.freedom.employee_management_app.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationInfo {
    private String fullName;
    private String employeeId;
    private String email;
    private String token;
}
