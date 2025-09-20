package com.amverma.student_service.controller;

import com.amverma.student_service.model.Student;
import com.amverma.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return studentService.delete(id);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return studentService.get(id);
    }

    @PostMapping("get")
    public ResponseEntity<?> get(@RequestBody List<Integer> ids) {
        return studentService.get(ids);
    }
}
