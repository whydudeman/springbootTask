package com.javaee.project.project3.controller;

import com.javaee.project.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (principal instanceof UserDetails) {
           authorities  = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
           for (GrantedAuthority authority:authorities){
               System.out.println(authority.getAuthority());
           }
        }

        return "auth/index";
    }

    @RequestMapping("login")
    private String login(Model model) {
        return "auth/login";
    }

    @RequestMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "auth/login";
    }

    @GetMapping("/main")
    private String getMainPage(Model model) {
        return "auth/index";
    }


}
