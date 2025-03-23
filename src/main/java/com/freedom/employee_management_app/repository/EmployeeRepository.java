package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String id);
    Optional<Employee> findByEmployeeId(String employeeId);
    Optional<Employee> findByResetPasswordToken(String resetPasswordToken);
    List<Employee> findByRole(String admin);
    boolean existsByEmployeeIdIgnoreCase(String employeeId);
}
