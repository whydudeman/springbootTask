package com.javaee.project.project2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String  surname;
    private Integer yearOfAdmission;

    @ManyToMany
    @JoinTable(
            joinColumns=@JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="course_id", referencedColumnName="id"))
    private List<Course> courses;
    @ManyToMany
    @JoinTable(
            joinColumns=@JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="group_id", referencedColumnName="id"))
    private List<Group> groups;
}

