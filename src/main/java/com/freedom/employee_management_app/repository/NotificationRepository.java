package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
