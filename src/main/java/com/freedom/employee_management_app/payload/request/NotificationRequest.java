package com.freedom.employee_management_app.payload.request;

import java.util.Map;

public class NotificationRequest {
    private Long employeeId;

    private String eventType;

    private Map<String, String> eventDetails;
}
