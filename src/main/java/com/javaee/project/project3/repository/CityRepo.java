package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City,Long> {
}
