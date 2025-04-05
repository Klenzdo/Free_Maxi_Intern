package com.freedom.employee_management_app.utils;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUtils {
    private final EmployeeRepository employeeRepository;

    public SecurityUtils(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee getCurrentEmployee() {
        Employee employee = (Employee) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    return employee;
    }

    public boolean isCurrentUserAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getAuthorities() != null){
            return authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }
}
