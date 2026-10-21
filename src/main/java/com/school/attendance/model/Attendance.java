package com.school.attendance.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance_records")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roll;
    private String date; // YYYY-MM-DD
    private String status; // Present, Absent, Late

    public Attendance() {}

    public Attendance(String roll, String date, String status) {
        this.roll = roll;
        this.date = date;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getRoll() { return roll; }
    public void setRoll(String roll) { this.roll = roll; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}