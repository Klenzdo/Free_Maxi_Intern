package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.dto.EmailDetails;

public interface NotificationService {

    void sendInternalNotification(String message);

    void sendEmailNotification(EmailDetails emailDetails);

    void notifyAdmin(String message);
}
