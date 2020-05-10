package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
