package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.auth.service.JwtService;
import com.freedom.employee_management_app.dto.EmailDetails;
import com.freedom.employee_management_app.dto.LoginRequestDto;
import com.freedom.employee_management_app.dto.LoginResponse;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.exception.EmployeeNotFoundException;
import com.freedom.employee_management_app.exception.InvalidPasswordException;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.EmployeeResponse;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.service.EmailService;
import com.freedom.employee_management_app.service.EmployeeService;
import com.freedom.employee_management_app.utils.SecurityUtils;
import com.freedom.employee_management_app.utils.TokenBlackListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service

public class EmployeeServiceImpl implements EmployeeService {


    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final TokenBlackListService tokenBlackListService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;
    private final SecurityUtils securityUtils;
    private final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());



    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JwtService jwtService, AuthenticationProvider authenticationProvider, PasswordEncoder passwordEncoder, TokenBlackListService tokenBlackListService, EmailService emailService, SecurityUtils securityUtils) {
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.tokenBlackListService = tokenBlackListService;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
        this.securityUtils = securityUtils;

    }

    @Override
    public ApiResponse<LoginResponse> login(LoginRequestDto request) throws Exception{
//       Employee e = employeeRepository.findByEmployeeId(request.getEmployeeId())
//                       .orElseThrow(()-> new EmployeeNotFoundException("Employee does not exist"));
//        if (!passwordEncoder.matches(e.getPassword(), request.getPassword())) {
//            throw new InvalidPasswordException("Incorrect password");
//        }
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

    @Override
    public ApiResponse<String> updatePassword( String newPassword, String oldPassword) throws EmployeeNotFoundException, InvalidPasswordException {

        Employee employee = securityUtils.getCurrentEmployee();

        if(employee == null){
            throw new EmployeeNotFoundException("Employee not found");
        }
        if (!passwordEncoder.matches(oldPassword, employee.getPassword())){
            throw new InvalidPasswordException("Incorrect old password");
        }
        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);





        return new ApiResponse<>("Password successfully updated", null);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
       return employeeRepository.findAll().stream()
               .map(employee -> new EmployeeResponse(
                        employee.getFullName(),
                        employee.getEmployeeId(),
                        employee.getEmail(),
                        employee.getRole()))
                .collect(Collectors.toList());

    }


}



