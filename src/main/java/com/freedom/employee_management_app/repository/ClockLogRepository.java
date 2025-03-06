package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.ClockLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface ClockLogRepository extends JpaRepository<ClockLog, Long> {

    ClockLog findClockLogByEmployeeIdAndClockInTimeBetween(Long employeeId, LocalDateTime from, LocalDateTime to);

    @Query("SELECT c FROM ClockLog c WHERE c.employee.id = :employeeId " +
    "AND c.clockInTime BETWEEN :startDate AND :endDate")
    Page<ClockLog> findClockLogsByEmployeeAndDateRange(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
             Pageable pageable);

}
