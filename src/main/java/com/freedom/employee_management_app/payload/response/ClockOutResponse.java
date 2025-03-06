package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.entity.ClockLog;
import com.freedom.employee_management_app.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClockOutResponse {
    private LocalDateTime clockOutTime;


    public ClockOutResponse(ClockLog clockLog){
        clockOutTime = clockLog.getClockOutTime();

    }

    public LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

}
