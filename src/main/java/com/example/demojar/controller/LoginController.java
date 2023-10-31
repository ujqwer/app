package com.example.demojar.controller;


import com.example.demojar.payloads.UserLoginDTO;
import com.example.demojar.repositories.UserRepository;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private DefaultUserService userService;

    @Autowired
    UserRepository userRepo;

    @ModelAttribute("user")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping
    public String login() {

        return "login";
    }

    @PostMapping
    public void loginUser(@ModelAttribute("user")
                          UserLoginDTO userLoginDTO) {
        System.out.println("UserDTO"+userLoginDTO);
        userService.loadUserByUsername(userLoginDTO.getUsername());
    }


}