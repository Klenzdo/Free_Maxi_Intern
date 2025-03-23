package com.freedom.employee_management_app.service.Impl;

import com.freedom.employee_management_app.auth.service.JwtService;
import com.freedom.employee_management_app.dto.*;
import com.freedom.employee_management_app.entity.Employee;
import com.freedom.employee_management_app.entity.Leave;
import com.freedom.employee_management_app.payload.request.LoginRequest;
import com.freedom.employee_management_app.payload.response.ApiResponse;
import com.freedom.employee_management_app.payload.response.EmployeeResponse;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import com.freedom.employee_management_app.repository.LeaveRepository;
import com.freedom.employee_management_app.service.AdminService;
import com.freedom.employee_management_app.service.EmailService;
import com.freedom.employee_management_app.utils.SecurityUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;



@Service
@PreAuthorize("hasRole('ADMIN')")
public class AdminServiceImpl implements AdminService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtils securityUtils;
    private final LeaveRepository leaveRepository;

    public AdminServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, EmailService emailService, JwtService jwtService, AuthenticationProvider authenticationProvider, SecurityUtils securityUtils, LeaveRepository leaveRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
        this.authenticationProvider = authenticationProvider;
        this.securityUtils = securityUtils;
        this.leaveRepository = leaveRepository;
    }

    private final EmailService emailService;

    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;


    @Override
    public CreateEmployeeDto createEmployee(EmployeeInfo employeeInfo) {


        String employeeId = "EMP -" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFullName(employeeInfo.getFullName());
        employee.setEmail(employeeInfo.getEmail());
        employee.setRole(employeeInfo.getRole());
        employee.setPassword(passwordEncoder.encode(generateDefaultPassword()));


        Employee savedEmployee = employeeRepository.save(employee);

        // Construct the details of email using EmailDetails object
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(employee.getEmail());
        emailDetails.setEmailSubject("Welcome to the Maxi-Farm Company");
        emailDetails.setEmailBody(String.format(
                "Your account has been created.\n\n" +
                        "Employee ID: %s\n" +
                        "Password:  %s\n\n" +
                        "Please login and update your password.",
                employeeId, generateDefaultPassword()));


        // send email notification with login detail
        emailService.sendEmail(emailDetails);
        return new CreateEmployeeDto(savedEmployee);
    }


    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {

        System.out.println("About to enter login");
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Employee admin = (Employee) authentication.getPrincipal();
//    employeeRepository.findByEmployeeId(request.getEmployeeId()).orElseThrow(()->new EntityNotFoundException("Employee with id " + request.getEmployeeId() + " does not exist"));
        String jwt = jwtService.generateToken(admin, admin.getEmployeeId());
        return new ApiResponse<>("Successful login", new LoginResponse(jwt));
    }

    @Override
    public ApiResponse<String> updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid status. Use APPROVED or REJECTED.");
        }

        leave.setStatus(status);
        leaveRepository.save(leave);

        return new ApiResponse<>("Leave request has been " + status.toLowerCase(), null);
    }

//    @Override
//    public ApiResponse<Employee> notifyAdmin(Employee employee) {
//        List<Employee> admins = employeeRepository.findByRole("ADMIN");
//        for (Employee admin : admins) {
//            EmailDetails emailDetails = new EmailDetails(
//                    admin.getEmail(),
//
//           "Employee: " + employee.getFullName() + "with Employee ID: " + employee.getEmployeeId() + "Account is Locked", "Employee Account Locked");
//            emailService.sendEmail(emailDetails);
//
//        }
//        return new ApiResponse<>("Admin notification sent successfully",  employee);
//    }


    private String generateDefaultPassword() {

        return "123456";
    }
}








