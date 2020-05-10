package com.javaee.project.project3.service;

import com.javaee.project.project3.form.AuthForm;
import com.javaee.project.project3.form.RegistrationForm;
import com.javaee.project.project3.model.User;
import com.javaee.project.project3.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity create(RegistrationForm registrationForm){
        User user=new User();
        user.setUsername(registrationForm.getUsername());
        String password=passwordEncoder.encode(registrationForm.getPassword());
        String name=registrationForm.getName();
        String surname=registrationForm.getSurname();
        String email=registrationForm.getEmail();
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user=userRepo.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    public User getByUsernameOrEmail(String identificator){
        return userRepo.findByUsernameOrEmail(identificator,identificator)
                .orElseThrow(()->new RuntimeException("NO_USER_FOUND"));
    }



}