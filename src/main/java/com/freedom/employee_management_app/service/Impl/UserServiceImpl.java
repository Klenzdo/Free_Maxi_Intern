package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.enums.Roles;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Override
    public void createAdminAccount() {
        if (employeeRepository.findByEmail("admin@example.com").isEmpty()){
            Employee admin = new Employee();
            admin.setFullName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Roles.ADMIN);

            employeeRepository.save(admin);
            System.out.println("Admin account created successfully");
        }

    }
}
