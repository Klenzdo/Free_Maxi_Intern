package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Optional<Leave> findById(Long id);
}
