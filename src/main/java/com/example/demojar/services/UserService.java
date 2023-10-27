package com.example.demojar.services;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User updateUser(User user, Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long userId);
}
