package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.UserSettings;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.repository.UserSettingRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class MonthlyResetScheduler {

    private final UserSettingRepository userSettingRepository;
    private  final EmployeeRepository employeeRepository;

    public MonthlyResetScheduler(UserSettingRepository userSettingRepository, EmployeeRepository employeeRepository) {
        this.userSettingRepository = userSettingRepository;
        this.employeeRepository = employeeRepository;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetLockedAccounts(){
        System.out.println(" ============================================================== ");
        System.out.println("Schedule started");
        List<UserSettings> lockedAccounts = userSettingRepository.findLockedAccounts();
        System.out.println("Locked account size " + lockedAccounts.size());
        for(UserSettings settings : lockedAccounts){
            Employee employee = settings.getEmployee();
            employee.setLocked(false);
            settings.resetLateCount();

            employeeRepository.save(employee);
            userSettingRepository.save(settings);

            System.out.println(" ============================================================== ");
            System.out.println("Schedule ended");
        }
    }
}
