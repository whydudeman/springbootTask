package com.javaee.project.project3.controller;

import com.javaee.project.project3.form.AuthForm;
import com.javaee.project.project3.form.RegistrationForm;
import com.javaee.project.project3.model.User;
import com.javaee.project.project3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public RegistrationForm userRegistrationDto() {
        return new RegistrationForm();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("regForm",userRegistrationDto());
        return "auth/register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("regForm") @Valid RegistrationForm regForm,
                                      BindingResult result) {

        User existing = userService.getByUsernameOrEmail(regForm.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (result.hasErrors()) {
            return "auth/register";
        }

        userService.create(regForm);
        return "auth/index";
    }
}