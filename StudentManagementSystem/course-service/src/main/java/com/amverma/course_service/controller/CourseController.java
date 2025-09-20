package com.amverma.course_service.controller;

import com.amverma.course_service.model.Course;
import com.amverma.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Course course) {
        return courseService.add(course);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Course course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return courseService.delete(id);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return courseService.get(id);
    }

    @PostMapping("get")
    public ResponseEntity<?> get(@RequestBody List<Integer> ids) {
        return courseService.get(ids);
    }
}
