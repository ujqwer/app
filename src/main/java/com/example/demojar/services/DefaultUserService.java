package com.example.demojar.services;

import com.example.demojar.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultUserService extends UserDetailsService {

    User save(User user);
    User findUserById(Integer id);
    User findUserByEmail(String email);

    User updateUser(User user);


}