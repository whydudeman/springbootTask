package com.javaee.project.project2.repository;

import com.javaee.project.project2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
