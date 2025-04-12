package com.freedom.employee_management_app.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadFile (MultipartFile file);
}
