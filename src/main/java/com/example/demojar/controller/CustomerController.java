package com.example.demojar.controller;

import com.example.demojar.Customer;
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
    private static final List<Customer> CUSTOMERS = List.of(
            new Customer(1L, "john", "doe", "john@javawhizz.com"),
            new Customer(2L, "mary", "public", "mary@javawhizz.com"),
            new Customer(3L, "elon", "musk","elon@javawhizz.com"),
            new Customer(4L, "dunny","duncan","dunny@javawhizz.com")
    );
//    @GetMapping
//    public List<Customer> findAllCustomers(){
//        return CUSTOMERS;
//    }
    @GetMapping
    public String method()
    {
        return "Hello Ujjman!!";
    }


    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
}