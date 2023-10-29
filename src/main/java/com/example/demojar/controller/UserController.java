package com.example.demojar.controller;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.services.DefaultUserService;
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
    @Autowired
    private DefaultUserService defaultUserService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @GetMapping("/hello")
    public String method()
    {
        return "Hello ujjman!!";
    }

    @PostMapping("/getuserfromid")
    public ResponseEntity<User> getUserFromId(@RequestBody Object id)
    {

        int a=Integer.parseInt(id.toString());

        return new ResponseEntity<>(this.defaultUserService.findUserById(a), HttpStatus.CREATED);
    }

}
