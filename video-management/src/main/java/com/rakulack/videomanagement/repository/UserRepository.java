package com.rakulack.videomanagement.repository;

import java.util.Optional;

import com.rakulack.videomanagement.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
