package com.example.demojar.controller;

import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.services.DefaultUserService;
import com.example.demojar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping("/get_user_from_id")
    public ResponseEntity<User> getUserFromId(@RequestBody Object id)
    {

        int a=Integer.parseInt(id.toString());

        return new ResponseEntity<>(this.defaultUserService.findUserById(a), HttpStatus.CREATED);
    }

    @GetMapping("/get_current_user")
    public ResponseEntity<User> getCurrentUser()
    {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User userDetails = (DefaultOAuth2User ) auth.getPrincipal();
        String email=userDetails.getAttribute("email");

        return new ResponseEntity<>(this.defaultUserService.findUserByEmail(email), HttpStatus.CREATED);
    }

}
