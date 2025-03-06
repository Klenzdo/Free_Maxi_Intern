package com.freedom.employee_management_app.repository;

import com.freedom.employee_management_app.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
        Optional<VerificationToken> findByToken(String token);

    }


