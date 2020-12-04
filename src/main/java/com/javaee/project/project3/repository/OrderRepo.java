package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.username=:username")
    List<Order> findAllByUsername(@Param(value = "username") String username);
}
