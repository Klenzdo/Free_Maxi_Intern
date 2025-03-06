package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestResponse {
    private String type;
    private String details;
    private String status = ""; // check after implementation
    private Employee employee;
}
