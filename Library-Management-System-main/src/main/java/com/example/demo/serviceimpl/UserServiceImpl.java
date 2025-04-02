package com.example.demo.serviceimpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(UserDTO userDTO) {
        // Create and save the new user
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Store the password as is (no encryption)
        user.setRole(userDTO.getRole()); // Assign role (Admin or Student)
        return userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(UserDTO userDTO) {
        // Find user by username
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user != null && user.getPassword().equals(userDTO.getPassword())) {
            return true; // Successful authentication
        }
        return false; // Invalid credentials
    }
}
