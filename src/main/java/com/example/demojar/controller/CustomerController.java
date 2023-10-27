package com.example.demojar.controller;

import com.example.demojar.Customer;
import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserDto;
import com.example.demojar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

//    @GetMapping
//    public List<Customer> findAllCustomers(){
//        return CUSTOMERS;
//    }
    @GetMapping
    public String method()
    {
        return "Hello baby!!";
    }


    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}