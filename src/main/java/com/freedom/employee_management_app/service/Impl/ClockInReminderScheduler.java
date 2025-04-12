package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.dto.EmailDetails;
import com.freedom.employee_management_app.entity.ClockLog;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.Notification;
import com.freedom.employee_management_app.repository.ClockLogRepository;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.repository.LeaveRepository;
import com.freedom.employee_management_app.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@EnableScheduling
public class ClockInReminderScheduler {

    private final EmployeeRepository employeeRepository;
    private final ClockLogRepository clockLogRepository;
    private final LeaveRepository leaveRepository;
    private final NotificationService notificationService;

    @Value("${admin.email}")
    private String adminEmail;


    public ClockInReminderScheduler(EmployeeRepository employeeRepository, ClockLogRepository clockLogRepository, LeaveRepository leaveRepository, NotificationService notificationService) {
        this.employeeRepository = employeeRepository;
        this.clockLogRepository = clockLogRepository;
        this.leaveRepository = leaveRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 15 * * MON-FRI")
    public void sendClockInReminder(){
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        List<Employee> allEmployees = employeeRepository.findAll();

        for(Employee employee : allEmployees){

            boolean isOnLeave = leaveRepository.existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                    employee.getId(), startOfDay, endOfDay
            );
            if (isOnLeave) continue;

            ClockLog log = clockLogRepository.findClockLogByEmployeeIdAndClockInTimeBetween(employee.getId(), startOfDay, endOfDay);
            if(log !=null) continue;

            String subject = "Missed Clock-In Alert";
            String body = String.format("Hi %s,\n\n You did not clock in today (%s). Please ensure you do not skip clock-in on workdays. \n\n Warmest Regards, \nHR  Department",
                    employee.getFullName(), today
            );

            EmailDetails toEmployee = new EmailDetails(employee.getEmail(), body, subject);
            EmailDetails toAdmin = new EmailDetails(adminEmail, "Employee" + employee.getFullName() + "failed to clock in " + today, subject);

            String message = "Employee" + employee.getFullName() + "(ID: " + employee.getEmployeeId() + ") failed to clock in " + today;
            notificationService.notifyAdmin(message);



            notificationService.sendEmailNotification(toEmployee);
            notificationService.sendEmailNotification(toAdmin);

        }
    }
}




