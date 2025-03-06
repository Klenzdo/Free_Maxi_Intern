package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.VerificationToken;
import com.freedom.employee_management_app.exception.InvalidTokenException;
import com.freedom.employee_management_app.repository.VerificationTokenRepository;
import com.freedom.employee_management_app.service.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {


    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public String generateVerificationToken(Employee employee) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setEmployee(employee);
        verificationToken.setExpiryTime(LocalDateTime.now().plusHours(24));
        verificationTokenRepository.save(verificationToken);
        return token;

    }

    @Override
    public VerificationToken validateToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Invalid token"));

    }
}
