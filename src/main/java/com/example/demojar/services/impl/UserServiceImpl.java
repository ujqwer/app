package com.example.demojar.services.impl;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.repositories.UserRepo;
import com.example.demojar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);

        return user;
    }

    @Override
    public User updateUser(User user, Long id) {
        return null;
    }



    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long userId) {

    }

    public User dtoToUser(UserDto dto)
    {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public  UserDto userToDto(User user)
    {
        UserDto dto=new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
