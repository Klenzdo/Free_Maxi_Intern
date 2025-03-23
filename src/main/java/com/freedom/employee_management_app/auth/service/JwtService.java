package com.freedom.employee_management_app.auth.service;

import com.freedom.employee_management_app.utils.TokenBlackListService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${secret-key}")
    private  String secretKey ;
    private final TokenBlackListService tokenBlackListService;

    public JwtService(TokenBlackListService tokenBlackListService) {
        this.tokenBlackListService = tokenBlackListService;
    }
//    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();

    //Extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
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
                .claims(extractClaims)
                .subject(employeeId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() *1000 *60 *24))
                .signWith(getSignInKey(), Jwts.SIG.HS256 )
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


}



