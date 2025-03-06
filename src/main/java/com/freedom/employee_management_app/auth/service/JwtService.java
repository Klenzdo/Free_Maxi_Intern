package com.freedom.employee_management_app.auth.service;

import com.freedom.employee_management_app.utils.TokenBlackListService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final static String SECRET_KEY = "MIHcAgEBBEIBalbNcwF1mb/0RLmXcY2tvVlYUzNAQEj7+SJVEOZm1fy/+LJcaM8ziKdyDF2GjlNjNll+ddfMf0qiyi0DXJiR8HKgBwYFK4EEACOhgYkDgYYABACjuzJ+k2yr5Uy+T9Wqi5NQ49nCkOb/8po6LmUD0g1QEoAQVDYBm7H8oVxqOqRQRdRfAvNyIBSp7exGBEaRDvpBxwBSv9N+liDQTecWCpssRDG9qED7TGtduV1AXPABiHMehOIqyVTxwTHzhUwyIpNEMI2+8IVC/7rmkiXb3YU+bfqamQ==";
    private final TokenBlackListService tokenBlackListService;

    public JwtService(TokenBlackListService tokenBlackListService) {
        this.tokenBlackListService = tokenBlackListService;
    }
//    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    //Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Extract single claims

    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // Extract username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // generate token for claims and user details
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails, String employeeId) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(employeeId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() *1000 *60 *24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256 )
                .compact();

    }

    public String generateToken(UserDetails userDetails, String employeeId) {
        return (generateToken(new HashMap<>(), userDetails, employeeId));
    }

    // check if token is valid
    public Boolean isTokenValid(String token, UserDetails userDetails ) {
        final String username = extractUsername(token);
        
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token)
        && !tokenBlackListService.isTokenRevoked(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

//    public void blacklistToken(String token) {
//        blacklistedTokens.add(token);
//    }
//
//    public boolean isBlacklisted(String token) {
//        return blacklistedTokens.contains(token);
//    }

}



