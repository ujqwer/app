package com.example.demojar.services.impl;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.repositories.UserRepo;
import com.example.demojar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto user) {

        User user1=this.dtoToUser(user);
        this.userRepo.save(user1);

        return this.userToDto(user1);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {

        return null;
    }

    @Override
    public UserDto getUserById(Integer id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

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
