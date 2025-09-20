package com.amverma.enrollment_service.controller;

import com.amverma.enrollment_service.model.Enrollment;
import com.amverma.enrollment_service.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Enrollment enrollment) {
        return enrollmentService.add(enrollment);
    }

    @GetMapping("student/{id}")
    public ResponseEntity<?> getAllCourses(@PathVariable int id) {
        return enrollmentService.getAllCourses(id);
    }

    @GetMapping("course/{id}")
    public ResponseEntity<?> getAllStudents(@PathVariable int id) {
        return enrollmentService.getAllStudents(id);
    }

    @DeleteMapping("/internal/course/{id}")
    public ResponseEntity<?> deleteByCourseId(@PathVariable int id) {
        return enrollmentService.deleteByCourseId(id);
    }

    @DeleteMapping("/internal/student/{id}")
    public ResponseEntity<?> deleteByStudentId(@PathVariable int id) {
        return enrollmentService.deleteByStudentId(id);
    }
}
