package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.dto.EmailDetails;

public interface EmailService {
    void sendEmail(EmailDetails emailDetails);

//    void sendEmail(String email, String emailSubject, String emailBody);
}
