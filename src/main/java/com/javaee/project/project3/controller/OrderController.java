package com.javaee.project.project3.controller;

import com.javaee.project.auth.UserDetailsImpl;
import com.javaee.project.project3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getAll(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetailsImpl sds = ((UserDetailsImpl) principal);
        model.addAttribute("orders", orderService.getAllByUsername(sds.getUsername()));
        return "order/order";
    }
}
