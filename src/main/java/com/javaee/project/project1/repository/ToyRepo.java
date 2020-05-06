package com.javaee.project.project1.repository;

import com.javaee.project.project1.model.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepo extends JpaRepository<Toy, Long> {
}
