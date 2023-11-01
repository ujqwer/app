package com.example.demojar.services.impl;



import com.example.demojar.entities.User;
import com.example.demojar.repositories.UserRepository;
import com.example.demojar.services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DefaultUserServiceImpl implements DefaultUserService {
    @Autowired
    private UserRepository userRepo;




    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DefaultUserServiceImpl() {
    }



    @Override
    public User findUserByEmail(String email){
        User user = userRepo.findByEmail(email);
        if(user==null) {
            throw new UsernameNotFoundException("Invalid user email.");
        }

        return user;

    }

    @Override
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findUserById(Integer id) throws UsernameNotFoundException {

        Optional<User> userobj = userRepo.findById(id);
        User user;
        if(userobj.isEmpty()) {
            throw new UsernameNotFoundException("Invalid user id.");
        }
        else {
            user=userobj.get();
        }
        return user;
    }



    @Override
    public User save(User user) {


        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}