package com.javaee.project.project3.controller;

import com.javaee.project.auth.UserDetailsImpl;
import com.javaee.project.project3.form.RegistrationForm;
import com.javaee.project.project3.model.Role;
import com.javaee.project.project3.model.User;
import com.javaee.project.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getAll(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users= new ArrayList<>();
        UserDetailsImpl sds = ((UserDetailsImpl) principal);
        if (sds.getAuthorities().stream().anyMatch(o->o.getAuthority().contains("ROLE_ADMIN")))
            users=userService.getAll();
        else if(sds.getAuthorities().stream().anyMatch(o->o.getAuthority().contains("ROLE_USER")))
            users.add(userService.getByUsernameOrEmail(sds.getUsername()));
        model.addAttribute("users",users);
        return "User";
    }

    @GetMapping(value = "edit/{id}")
    public String editPage(@PathVariable(name = "id") Long id, Model model){
        User user =userService.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("userForm", new RegistrationForm());
        return "editUser";
    }

    @PostMapping(value = "edit/{id}")
    public String updatePage(@PathVariable(name = "id") Long id, @ModelAttribute RegistrationForm userForm){
        userService.updateUser(id,userForm);
        return "redirect:/user";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){
        userService.deleteById(id);
        return "redirect:/user";
    }


}
