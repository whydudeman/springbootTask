package com.javaee.project.project2.repository;

import com.javaee.project.project2.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Long> {
}
