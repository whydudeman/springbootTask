package com.javaee.project.project3.service;

import com.javaee.project.project3.model.Order;
import com.javaee.project.project3.model.Price;
import com.javaee.project.project3.model.User;
import com.javaee.project.project3.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private PriceService priceService;
    @Autowired
    private UserService userService;

    public List<Order> getAll() {
       return orderRepo.findAll();
    }

    public List<Order> getAllByUsername(String username) {
        return orderRepo.findAllByUsername(username);
    }

    public Order create(Long priceId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User sds = ((User) principal);
        String username=sds.getUsername();
        User user = userService.getByUsernameOrEmail(username);
        Order order=new Order();
        Price price=priceService.getPriceById(priceId);
        order.setPrice(price);
        order.setUser(user);
        order.setQuantity(1);
       return orderRepo.save(order);

    }
}
