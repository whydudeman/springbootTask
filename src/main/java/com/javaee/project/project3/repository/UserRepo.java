package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username,String email);

}
