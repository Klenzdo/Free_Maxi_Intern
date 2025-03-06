package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.auth.service.JwtService;
import com.freedom.employee_management_app.dto.LoginRequestDto;
import com.freedom.employee_management_app.dto.LoginResponse;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.service.EmployeeService;
import com.freedom.employee_management_app.utils.TokenBlackListService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final TokenBlackListService tokenBlackListService;



    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JwtService jwtService, AuthenticationProvider authenticationProvider, PasswordEncoder passwordEncoder, TokenBlackListService tokenBlackListService) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.tokenBlackListService = tokenBlackListService;
    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequestDto request) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmployeeId(),
                        request.getPassword()
                )
        );
        Employee employee = (Employee) authentication.getPrincipal();
        String jwt = jwtService.generateToken(employee, employee.getEmployeeId());
        return new ApiResponse<> ("Successful login", new LoginResponse(jwt));
    }

    @Override
    public ApiResponse<String> logout( String authHeader) {

        if(authHeader == null) {
            return new ApiResponse<> ("Invalid token", null);
        }
        String token =authHeader.substring(7);

        if(tokenBlackListService.isTokenRevoked(token)){
            return new ApiResponse<>("Token already revoked", null);
        }
        tokenBlackListService.revokeToken(token);
      return new ApiResponse<>("Logout successful", null);

    }


}



