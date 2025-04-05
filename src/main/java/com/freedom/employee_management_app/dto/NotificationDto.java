package com.freedom.employee_management_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freedom.employee_management_app.enums.NotificationStatus;

import java.time.LocalDateTime;

public class NotificationDto {
    private Long employeeId;

    private String title;

    private String message;

    private Long notifications;

    @JsonFormat(pattern = "HH:mm dd-MM")
    private LocalDateTime timestamp;

    private NotificationStatus status;

    public NotificationDto(Long employeeId, String title, String message, Long notifications, LocalDateTime timestamp, NotificationStatus status) {
        this.employeeId = employeeId;
        this.title = title;
        this.message = message;
        this.notifications = notifications;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getNotifications() {
        return notifications;
    }

    public void setNotifications(Long notifications) {
        this.notifications = notifications;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }
}
