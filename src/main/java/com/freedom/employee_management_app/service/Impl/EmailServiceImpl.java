package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.dto.EmailDetails;
import com.freedom.employee_management_app.exception.EmailAlreadyExistsException;
import com.freedom.employee_management_app.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;

    }

    @Value("${spring.mail.username}")
    private String senderEmail;



    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setText(emailDetails.getEmailBody());
            simpleMailMessage.setSubject(emailDetails.getEmailSubject());

            javaMailSender.send(simpleMailMessage);
            System.out.println("Email sent successfully");
        }catch (Exception e){
            throw new EmailAlreadyExistsException("Email not sent");
        }

    }
}
