package com.javaee.project.project2.service;

import com.javaee.project.project2.form.CourseDto;
import com.javaee.project.project2.model.Course;
import com.javaee.project.project2.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    public Course saveByDto(CourseDto courseDto) {
        Course course=new Course();
        course.setName(courseDto.getName());
        course.setCredit(courseDto.getCredit());
        return courseRepo.save(course);
    }

    public Course save(Course course) {
        return courseRepo.save(course);
    }

    public void deleteById(Long id) {
        courseRepo.deleteById(id);
    }

    public Course getById(Long id) {
        return courseRepo.findById(id).orElseThrow(()->new RuntimeException("NO_COURSE_FOUND"));
    }
}
