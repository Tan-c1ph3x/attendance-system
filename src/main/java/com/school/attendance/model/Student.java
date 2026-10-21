package com.school.attendance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
    @Id
    private String roll;
    private String name;
    private String studentClass;

    public Student() {}

    public Student(String roll, String name, String studentClass) {
        this.roll = roll;
        this.name = name;
        this.studentClass = studentClass;
    }

    public String getRoll() { return roll; }
    public void setRoll(String roll) { this.roll = roll; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStudentClass() { return studentClass; }
    public void setStudentClass(String studentClass) { this.studentClass = studentClass; }
}