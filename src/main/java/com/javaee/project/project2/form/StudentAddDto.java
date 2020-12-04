package com.javaee.project.project2.form;

public class StudentAddDto {
    private Long studentId;
    private Long objectId;

    public StudentAddDto(Long studentId){
        this.studentId=studentId;
    }

    public StudentAddDto() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

}
