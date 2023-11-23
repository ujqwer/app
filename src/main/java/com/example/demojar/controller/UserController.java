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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private DefaultUserService defaultUserService;


    @GetMapping("/hello")
    public String method() {
        return "Hello ujjman!!";
    }

    @PostMapping("/getUserFromId")
    public ResponseEntity<User> getUserFromId(@RequestBody Object id) {

        int a = Integer.parseInt(id.toString());

        return new ResponseEntity<>(this.defaultUserService.findUserById(a), HttpStatus.CREATED);
    }


    @GetMapping("/existsByEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        try {
            this.defaultUserService.findUserByEmail(email);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

    }

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        try {
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }


    @GetMapping("/getUserFromEmail")
    public ResponseEntity<User> getUserFromEmail(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody User user) {
        try {
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getItemsPurchased")
    public ResponseEntity<List<String>> getItemsPurchased(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            List<String> items = user.getItemsPurchased();
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setItemsPurchased")
    public ResponseEntity<Boolean> setItemsPurchased(@RequestBody Map<String,String> request) {
        try {
            User user = this.defaultUserService.findUserByEmail(request.get("email"));
            List<String> items = new ArrayList<>(user.getItemsPurchased());
            items.add(request.get("itemPurchasedId"));
            user.setItemsPurchased(items);
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getItemsListed")
    public ResponseEntity<List<String>> getItemsListed(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            List<String> items = user.getItemsListed();
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setItemsListed")
    public ResponseEntity<Boolean> setItemsListed(@RequestBody Map<String, String> request) {
        try {
            User user = this.defaultUserService.findUserByEmail(request.get("email"));
            List<String> items = new ArrayList<>(user.getItemsListed());
            items.add(request.get("itemId"));
            user.setItemsListed(items);
            this.defaultUserService.save(user);
            System.out.println(user.getItemsListed());
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }


    }

    @GetMapping("/getVisibleTo")
    public ResponseEntity<List<String>> getVisibleTo(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            List<String> items = user.getVisibleTo();
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setVisibleTo")
    public ResponseEntity<Boolean> setVisibleTo(@RequestBody Map<String,String> request) {
        try {
            User user = this.defaultUserService.findUserByEmail(request.get("email"));
            List<String> visibleTo = new ArrayList<>(user.getVisibleTo());
            visibleTo.add(request.get("visibleToId"));
            user.setVisibleTo(visibleTo);
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }



    @GetMapping("/getItemsSold")
    public ResponseEntity<List<String>> getItemsSold(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            List<String> items = user.getItemsSold();
            return new ResponseEntity<>(items, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setItemsSold")
    public ResponseEntity<Boolean> setItemsSold(@RequestBody Map<String,String> request) {
        try {
            User user = this.defaultUserService.findUserByEmail(request.get("email"));
            List<String> items = new ArrayList<>(user.getItemsSold());
            items.add(request.get("itemId"));
            user.setItemsSold(items);
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }



    @GetMapping("/getCurrentBids")
    public ResponseEntity<List<String>> getCurrentBids(@RequestParam String email) {
        try {
            User user = this.defaultUserService.findUserByEmail(email);
            List<String> bids = user.getCurrentBids();
            return new ResponseEntity<>(bids, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/setCurrentBids")
    public ResponseEntity<Boolean> setCurrentBids(@RequestBody Map<String,String> request) {
        try {
            User user = this.defaultUserService.findUserByEmail(request.get("email"));
            List<String> bids = new ArrayList<>(user.getCurrentBids());
            bids.add(request.get("bidId"));
            user.setCurrentBids(bids);
            this.defaultUserService.save(user);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.CREATED);
        }
    }




}
