package com.freedom.employee_management_app.utils;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class TokenBlackListService {
    private final Set<String> revokedTokens = new HashSet<>();

    public void revokeToken(String token){
        revokedTokens.add(token);
    }
    public boolean isTokenRevoked(String token){
        return revokedTokens.contains(token);
    }
}
