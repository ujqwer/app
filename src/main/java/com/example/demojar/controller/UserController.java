package com.example.demojar.controller;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/hello")
    public String method()
    {
        return "Hello bbb!!";
    }

}
