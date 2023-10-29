package com.example.demojar.controller;



import com.example.demojar.entities.User;
import com.example.demojar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    UserRepository userRepo;
    User user;
    @GetMapping
    public String displayDashboard(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User oAuthUser = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
            User user = userRepo.findByEmail(oAuthUser.getAttribute("email"));
            this.user=user;
            model.addAttribute("userDetails", user.getName());
        }else {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) securityContext.getAuthentication().getPrincipal();
            User users = userRepo.findByEmail(user.getUsername());
            model.addAttribute("userDetails", users.getName());
        }
        return "dashboard";
    }

    @GetMapping("/getuser")
    @ResponseBody
    public User getUser()
    {
        return this.user;
    }

}