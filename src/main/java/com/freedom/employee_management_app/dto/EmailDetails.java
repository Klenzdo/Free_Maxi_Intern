package com.freedom.employee_management_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data



public class EmailDetails {

        private String recipient;
        private String emailBody;
        private String emailSubject;

    public EmailDetails(String recipient, String emailBody, String emailSubject) {
        this.recipient = recipient;
        this.emailBody = emailBody;
        this.emailSubject = emailSubject;
    }

    public EmailDetails() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }
}

