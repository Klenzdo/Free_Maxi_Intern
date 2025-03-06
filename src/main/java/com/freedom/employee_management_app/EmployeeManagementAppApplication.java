package com.freedom.employee_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {
//		"com.freedom.employee_management_app.controller",
//		"com.freedom.employee_management_app.service",
//		"com.freedom.employee_management_app.repository",
//		"com.freedom.employee_management_app.config"
//})
public class EmployeeManagementAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementAppApplication.class, args);
	}

}
