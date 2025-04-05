package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSettings, Long> {

    Optional<UserSettings> findByEmployeeId(Long employeeId);

    @Query("SELECT u FROM UserSettings u WHERE u.lateCount = 3")
    List<UserSettings> findLockedAccounts();
}
