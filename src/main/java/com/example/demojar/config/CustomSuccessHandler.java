package com.example.demojar.config;


import com.example.demojar.entities.User;
import com.example.demojar.payloads.UserRegisteredDTO;
import com.example.demojar.repositories.UserRepository;
import com.example.demojar.services.DefaultUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepo;

    @Autowired
    DefaultUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String redirectUrl = null;
        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
            String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
            if(userRepo.findByEmail(username) == null) {
                User user = new User();
                user.setEmail(username);
                user.setName(userDetails.getAttribute("name") !=null?userDetails.getAttribute("name"):userDetails.getAttribute("login"));
                user.setPassword(("Dummy"));
                userService.save(user);
                //if user does not exists then redirect to registration page
                redirectUrl = "http://localhost:3000/register";
            }
            else{
                //if user already exists then redirect to dashboard
                redirectUrl="https://cal.ai";
            }
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}