package com.school.attendance.controller;

import com.school.attendance.model.Attendance;
import com.school.attendance.model.Student;
import com.school.attendance.repository.AttendanceRepository;
import com.school.attendance.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final StudentRepository studentRepo;
    private final AttendanceRepository attendanceRepo;

    public AttendanceController(StudentRepository studentRepo, AttendanceRepository attendanceRepo) {
        this.studentRepo = studentRepo;
        this.attendanceRepo = attendanceRepo;
    }

    // --- Student Endpoints ---
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (studentRepo.existsById(student.getRoll())) {
            return ResponseEntity.badRequest().body("Roll number already exists.");
        }
        return ResponseEntity.ok(studentRepo.save(student));
    }

    @DeleteMapping("/students/{roll}")
    @Transactional
    public ResponseEntity<?> deleteStudent(@PathVariable String roll) {
        attendanceRepo.deleteByRoll(roll);
        studentRepo.deleteById(roll);
        return ResponseEntity.ok().build();
    }

    // --- Attendance Endpoints ---
    @GetMapping("/attendance")
    public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    @PostMapping("/attendance")
    public ResponseEntity<?> markAttendance(@RequestBody Attendance newRecord) {
        Optional<Attendance> existing = attendanceRepo.findByRollAndDate(newRecord.getRoll(), newRecord.getDate());
        if (existing.isPresent()) {
            Attendance att = existing.get();
            att.setStatus(newRecord.getStatus());
            return ResponseEntity.ok(attendanceRepo.save(att));
        }
        return ResponseEntity.ok(attendanceRepo.save(newRecord));
    }

    @DeleteMapping("/attendance")
    @Transactional
    public ResponseEntity<?> clearAttendance(@RequestParam String roll, @RequestParam String date) {
        attendanceRepo.deleteByRollAndDate(roll, date);
        return ResponseEntity.ok().build();
    }
}