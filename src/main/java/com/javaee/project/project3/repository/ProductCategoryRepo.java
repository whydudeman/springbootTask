package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory,Long> {
}
