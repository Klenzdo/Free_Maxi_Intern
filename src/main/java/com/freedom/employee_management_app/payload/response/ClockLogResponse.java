package com.freedom.employee_management_app.payload.response;

import com.freedom.employee_management_app.entity.ClockLog;


import java.time.LocalDateTime;


public class ClockLogResponse {
    private LocalDateTime clockInTime;
    private LocalDateTime clockOutTime;
    private boolean isLate;
//    private Employee employee;

//    public ClockLogResponse(LocalDateTime clockInTime, LocalDateTime clockOutTime, boolean isLate, Employee employee) {
        public ClockLogResponse(ClockLog log){
        this.clockInTime = log.getClockInTime();
        this.clockOutTime = log.getClockOutTime();
        this.isLate = log.isLate();
//        this.employee = employee;
    }

    public ClockLogResponse() {
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

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
