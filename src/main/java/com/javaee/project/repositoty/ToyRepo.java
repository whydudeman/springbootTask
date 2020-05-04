package com.javaee.project.repositoty;

import com.javaee.project.model.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepo extends JpaRepository<Toy, Long> {
}
