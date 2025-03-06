package com.freedom.employee_management_app.service;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.VerificationToken;

public interface VerificationTokenService {
    String generateVerificationToken(Employee employee);
    VerificationToken validateToken(String token);
}
