package com.javaee.project.project2.controller;

import com.javaee.project.project2.form.CourseDto;
import com.javaee.project.project2.form.GroupDto;
import com.javaee.project.project2.form.StudentAddDto;
import com.javaee.project.project2.form.StudentDto;
import com.javaee.project.project2.model.Course;
import com.javaee.project.project2.model.Group;
import com.javaee.project.project2.model.Student;
import com.javaee.project.project2.service.CourseService;
import com.javaee.project.project2.service.GroupService;
import com.javaee.project.project2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseUserGroupController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "course/all")
    public String getAllCourses(Model model) {

        CourseDto courseDto = new CourseDto();
        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        model.addAttribute("courseForm", courseDto);

        return "second/course";
    }
    @GetMapping(value = "course/redirect")
    public String redirectToAllCourses(){
        return "redirect:/course/all";
    }

    @GetMapping(value = "group/redirect")
    public String redirectToAllGroups(){
        return "redirect:/group/all";
    }


    @PostMapping(value = "createCourse")
    public String createCourse(@ModelAttribute CourseDto courseDto) {
        courseService.saveByDto(courseDto);

        return "redirect:/course/all";
    }

    @GetMapping(value = "editCoursePage/{id}")
    public String showUpdateFormForCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "second/courseEdit";
    }

    @PostMapping(value = "updateCourse/{id}")
    public String updateCourse(@PathVariable("id") Long id, @ModelAttribute Course course) {
        course.setId(id);
        courseService.save(course);
        return "redirect:/course/all";
    }

    @GetMapping(value = "deleteCourse/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        courseService.deleteById(id);
        return "redirect:/course/all";
    }


    @GetMapping(value = "group/all")
    public String getAllGroups(Model model) {

        GroupDto groupDto = new GroupDto();
        List<Group> groups = groupService.getAll();
        model.addAttribute("groups", groups);
        model.addAttribute("groupForm", groupDto);

        return "second/group";
    }

    @PostMapping(value = "createGroup")
    public String create(@ModelAttribute GroupDto groupDto) {

        groupService.createByDto(groupDto);
        return "redirect:group/all";
    }

    @GetMapping(value = "editGroupPage/{id}")
    public String showUpdateFormForGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "second/groupEdit";
    }

    @PostMapping(value = "updateGroup/{id}")
    public String updateGroup(@PathVariable("id") Long id, @ModelAttribute Group group) {
        groupService.save(group);
        return "redirect:/group/all";
    }

    @GetMapping(value = "deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") Long id, Model model) {
        groupService.deleteById(id);
        return "redirect:/group/all";
    }

    @GetMapping(value = "student/all")
    public String getAllStudents(Model model){
        List<Student> students=studentService.getAll();
        model.addAttribute("students",students);
        model.addAttribute("studentForm",new StudentDto());
        return "second/student";
    }

    @PostMapping(value = "createStudent")
    public String createStudent(@ModelAttribute StudentDto studentDto){
        studentService.createByDto(studentDto);
        return redirectToAllStudents();
    }


    @PostMapping(value = "updateStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.save(student);
        return "redirect:/student/all";
    }

    @GetMapping(value = "student/redirect")
    public String redirectToAllStudents(){
        return "redirect:/student/all";
    }

    @GetMapping(value = "student/{id}")
    public String getStudentById(@PathVariable("id") Long id,Model model){
        Long courseId = null;
        Long groupId=null;
        Student student=studentService.getById(id);
        model.addAttribute("student",student);
        model.addAttribute("courses",courseService.getAll());
        model.addAttribute("groups",groupService.getAll());
        model.addAttribute("adder",new StudentAddDto(id));
        return "second/studentEdit";
    }

    @GetMapping(value = "deleteStudent/{id}")
    public String deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteById(id);
        return redirectToAllStudents();
    }

    @GetMapping(value = "student/delete/{id}/course/{courseId}")
    public String deleteCourseFromStudent(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId){
        studentService.deleteCourseFromStudentById(id,courseId);
        return "redirect:/student/"+id;
    }

    @GetMapping(value = "student/delete/{id}/group/{groupId}")
    public String deleteGroupFromStudent(@PathVariable("id") Long id, @PathVariable("groupId") Long groupId){
        studentService.deleteGroupFromStudentById(id,groupId);
        return "redirect:/student/"+id;
    }

    @PostMapping(value = "studentAddCourse/{id}")
    public String addCourseToStudent(@PathVariable("id") Long id,@ModelAttribute(name = "adder") StudentAddDto studentAddDto){
        System.out.println(id+" id of student"+studentAddDto.getObjectId()+" id of object");
        studentService.addCourse(id,studentAddDto.getObjectId());
       return "redirect:/student/"+id;
    }

    @PostMapping(value = "studentAddGroup/{id}")
    public String addGroupToStudent(@PathVariable("id") Long id,@ModelAttribute(name = "adder") StudentAddDto studentAddDto){
        System.out.println(id+" id of student"+studentAddDto.getObjectId()+" id of object");
        studentService.addGroup(id,studentAddDto.getObjectId());
        return "redirect:/student/"+id;
    }



}
