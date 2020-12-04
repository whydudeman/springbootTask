package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> getAllByCategoryId(Long categoryId);

    @Query("select p from Product p where p.name like %:name%")
    List<Product> getAllByNameLike(@Param("name") String name);

}
