package com.javaee.project.project1.repository;

import com.javaee.project.project1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
