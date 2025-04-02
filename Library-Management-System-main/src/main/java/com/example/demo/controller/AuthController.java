package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Register a new user (Admin or Student)
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully with ID: " + user.getId());
    }

    // Login a user (Admin or Student)
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        boolean isAuthenticated = userService.authenticateUser(userDTO);
        if (isAuthenticated) {
            return ResponseEntity.ok("User logged in successfully");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
