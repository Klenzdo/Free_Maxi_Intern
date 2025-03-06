package com.freedom.employee_management_app.payload.response;

public class LogOutResponse {
    private boolean success;

    public LogOutResponse(boolean success) {
        this.success = success;
    }

    public LogOutResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
