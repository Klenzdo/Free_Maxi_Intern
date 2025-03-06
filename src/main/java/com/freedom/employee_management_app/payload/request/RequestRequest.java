package com.freedom.employee_management_app.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestRequest {
    private String type;
    private String details;
    private String status = "pending";
}
