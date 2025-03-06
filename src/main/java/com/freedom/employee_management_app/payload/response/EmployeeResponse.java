package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {

    private String fullName;
    private String employeeId;
    private String email;
    private Roles role;

}
