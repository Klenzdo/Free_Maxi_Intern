package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.dto.EmailDetails;
import com.freedom.employee_management_app.entity.Notification;
import com.freedom.employee_management_app.repository.NotificationRepository;
import com.freedom.employee_management_app.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    @Value("${admin.email}")
    private String adminEmail;


    public NotificationServiceImpl(JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;

    }


    @Override
    public void sendInternalNotification(String message) {
        Notification notification = new Notification();
        notificationRepository.save(notification);

    }

    @Override
    public void sendEmailNotification(EmailDetails emailDetails) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDetails.getRecipient());
        message.setSubject(emailDetails.getEmailSubject());
        message.setText(emailDetails.getEmailBody());
        mailSender.send(message);
    }


    @Override
    public void notifyAdmin(String message) {
        sendInternalNotification(message);

        EmailDetails emailDetails = new EmailDetails(
                getAdminEmail(), message, "Employee Account Locked"
        );
        sendEmailNotification(emailDetails);

    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
