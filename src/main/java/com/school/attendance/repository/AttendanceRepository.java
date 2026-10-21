package com.school.attendance.repository;

import com.school.attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByRollAndDate(String roll, String date);
    List<Attendance> findByRoll(String roll);
    void deleteByRoll(String roll);
    void deleteByRollAndDate(String roll, String date);
}