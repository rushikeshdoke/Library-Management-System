package com.library.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}