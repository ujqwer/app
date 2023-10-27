package com.example.demojar.repositories;

import com.example.demojar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);
}
