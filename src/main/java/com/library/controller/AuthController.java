package com.library.controller;

import com.library.model.Admin;
import com.library.model.Student;
import com.library.repo.AdminRepository;
import com.library.repo.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")  // Base path = /auth
public class AuthController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --------------------------
    // ADMIN Registration Endpoint
    // --------------------------
    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ADMIN");
        adminRepo.save(admin);
        return "Admin registered successfully!";
    }

    // ----------------------------
    // STUDENT Registration Endpoint
    // ----------------------------
    @PostMapping("/register-student")
    public String registerStudent(@RequestBody Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole("STUDENT");
        studentRepo.save(student);
        return "Student registered successfully!";
    }
}