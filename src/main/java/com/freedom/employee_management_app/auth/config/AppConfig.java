package com.freedom.employee_management_app.auth.config;

import com.freedom.employee_management_app.auth.service.CustomUserDetailsService;
import com.freedom.employee_management_app.exception.EmployeeIdNotFoundException;
import com.freedom.employee_management_app.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


    @Configuration
    public class AppConfig {

        private final EmployeeRepository employeeRepository;

        public AppConfig(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        @Bean
        public UserDetailsService userDetailsService(){
            return new CustomUserDetailsService(employeeRepository);
        }

//        @Bean
//        public UserDetailsService userDetailsService() {
//            return employeeId -> employeeRepository.findByEmployeeId(employeeId)
//                    .orElseThrow(() -> new EmployeeIdNotFoundException ("Employee Id not found"));
//        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setPasswordEncoder(passwordEncoder());
            authProvider.setUserDetailsService(userDetailsService());
            return authProvider;
        }
    }

