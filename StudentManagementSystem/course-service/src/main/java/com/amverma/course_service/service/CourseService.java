package com.amverma.course_service.service;

import com.amverma.course_service.dao.CourseDao;
import com.amverma.course_service.feign.EnrollmentInterface;
import com.amverma.course_service.model.Course;
import com.amverma.course_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private EnrollmentInterface enrollmentInterface;

    public ResponseEntity<?> add(Course course) {
        courseDao.save(course);
        return new ResponseEntity<>(new ApiResponse<>(true, course, "Course added successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> update(int id, Course course) {
        Course c = courseDao.findById(id).orElse(null);
        if(c == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Course does not exist!!!"), HttpStatus.OK);

        if(course.getTitle() != null) c.setTitle(course.getTitle());
        if(course.getCredits() != null) c.setCredits(course.getCredits());
        if(course.getPrice() != null) c.setPrice(course.getPrice());
        courseDao.save(c);
        return new ResponseEntity<>(new ApiResponse<>(true, c, "Course details updated successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(int id) {
        Course c = courseDao.findById(id).orElse(null);
        if(c == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Course does not exist!!!"), HttpStatus.OK);

        courseDao.deleteById(id);
        enrollmentInterface.deleteByCourseId(id);
        return new ResponseEntity<>(new ApiResponse<>(true, c, "Course deleted successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> get(int id) {
        Course c = courseDao.findById(id).orElse(null);
        if(c == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Course does not exist!!!"), HttpStatus.OK);

        return new ResponseEntity<>(new ApiResponse<>(true, c, "Course details got successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> get(List<Integer> ids) {
        List<Course> courses = new ArrayList<>();
        for(int id: ids) {
            courseDao.findById(id).ifPresent(courses::add);
        }
        return new ResponseEntity<>(new ApiResponse<>(true, courses, "Course details got successfully"), HttpStatus.OK);
    }
}
