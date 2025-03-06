package com.freedom.employee_management_app.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ClockLogRequest {
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private boolean isLate;

    public ClockLogRequest(LocalDateTime clockInTime, LocalDateTime clockOutTime, boolean isLate) {
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.isLate = isLate;
    }

    public ClockLogRequest() {
    }

    public LocalDateTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }
}
