package com.javaee.project.project2.service;

import com.javaee.project.project2.form.StudentDto;
import com.javaee.project.project2.model.Course;
import com.javaee.project.project2.model.Group;
import com.javaee.project.project2.model.Student;
import com.javaee.project.project2.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public Student getById(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new RuntimeException("NO_STUDENT_FOUND"));
    }

    public void deleteById(Long id) {
        studentRepo.deleteById(id);
    }

    public void deleteCourseFromStudentById(Long id, Long courseId) {
        Student student = getById(id);
        List<Course> courses = student.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                courses.remove(i);
                break;
            }
        }
        student.setCourses(courses);
        studentRepo.save(student);
    }

    public void deleteGroupFromStudentById(Long id, Long groupId) {
        Student student = getById(id);
        List<Group> groups = student.getGroups();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(groupId)) {
                groups.remove(i);
                break;
            }
        }
        student.setGroups(groups);
        studentRepo.save(student);
    }

    public Student createByDto(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setYearOfAdmission(studentDto.getYearOfAdmission());
        return studentRepo.save(student);
    }

    public void addCourse(Long id, Long courseId) {
        Student student = getById(id);
        List<Course> courses = student.getCourses();
        Course course = courseService.getById(courseId);
        courses.add(course);
        for (Course courser : courses) {
            System.out.println(courser.getName());
        }
        student.setCourses(courses);
        studentRepo.save(student);
    }

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    public void addGroup(Long id, Long objectId) {
        Student student = getById(id);
        List<Group> groups = student.getGroups();
        Group group = groupService.getGroupById((objectId));
        groups.add(group);
        for (Group grouper : groups) {
            System.out.println(grouper.getName());
        }
        student.setGroups(groups);
        studentRepo.save(student);
    }
}
