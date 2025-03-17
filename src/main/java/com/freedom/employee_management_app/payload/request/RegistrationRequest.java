package com.freedom.employee_management_app.payload.request;

import com.freedom.employee_management_app.enums.Gender;
import com.freedom.employee_management_app.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
    private String fullName;
    private String employeeId;
    private String email;
    private String password;
    private Gender gender;
    private Roles role;

}

