package com.freedom.employee_management_app.dto;

public class ResetPasswordRequest {
    private String tokenOrOtp;
    private String newPassword;

    public ResetPasswordRequest(String tokenOrOtp, String newPassword) {
        this.tokenOrOtp = tokenOrOtp;
        this.newPassword = newPassword;
    }

    public ResetPasswordRequest() {
    }

    public String getTokenOrOtp() {
        return tokenOrOtp;
    }

    public void setTokenOrOtp(String tokenOrOtp) {
        this.tokenOrOtp = tokenOrOtp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
