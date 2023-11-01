package com.example.demojar.controller;

import com.example.demojar.entities.User;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private DefaultUserService defaultUserService;



    @GetMapping("/hello")
    public String method()
    {
        return "Hello ujjman!!";
    }

    @PostMapping("/getUserFromId")
    public ResponseEntity<User> getUserFromId(@RequestBody Object id)
    {

        int a=Integer.parseInt(id.toString());

        return new ResponseEntity<>(this.defaultUserService.findUserById(a), HttpStatus.CREATED);
    }


    @GetMapping("/existsByEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        try {
            this.defaultUserService.findUserByEmail(email);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false,HttpStatus.OK);
        }

    }

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createUser(@RequestBody User user)
    {
        try {
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(false,HttpStatus.CREATED);
        }
    }


    @GetMapping("/getUserFromEmail")
    public ResponseEntity<User> getUserFromEmail(@RequestParam String email)
    {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (Exception e) {

            return new ResponseEntity<>(null,HttpStatus.OK);
        }

    }

    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody User user)
    {
        try {
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(false,HttpStatus.CREATED);
        }
    }




}
