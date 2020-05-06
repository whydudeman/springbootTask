package com.javaee.project.project2.repository;

import com.javaee.project.project2.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
