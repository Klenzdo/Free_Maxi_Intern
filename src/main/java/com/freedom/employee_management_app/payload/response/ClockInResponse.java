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
public class ClockInResponse {
    private LocalDateTime clockInTime;
    private boolean isLate;
//    private Employee employee;

    public ClockInResponse(ClockLog clockLog){
        this.clockInTime = clockLog.getClockInTime();
        this.isLate = clockLog.isLate();
//        employee = clockLog.getEmployee();
    }

    public LocalDateTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
