package com.amverma.enrollment_service.service;

import com.amverma.enrollment_service.dao.EnrollmentDao;
import com.amverma.enrollment_service.feign.CourseInterface;
import com.amverma.enrollment_service.feign.StudentInterface;
import com.amverma.enrollment_service.model.Enrollment;
import com.amverma.enrollment_service.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentDao enrollmentDao;
    @Autowired
    private StudentInterface studentInterface;
    @Autowired
    private CourseInterface courseInterface;

    public ResponseEntity<?> add(Enrollment enrollment) {
        Enrollment enroll = enrollmentDao.get(enrollment.getStudentId(), enrollment.getCourseId());
        if(enroll != null) return new ResponseEntity<>(new ApiResponse<>(false, enroll, "Student is already registered in the course"), HttpStatus.OK);

        ResponseEntity<ApiResponse<?>> student = studentInterface.get(enrollment.getStudentId());
        if(!student.getBody().isStatus()) return student;

        ResponseEntity<ApiResponse<?>> course = courseInterface.get(enrollment.getCourseId());
        if(!course.getBody().isStatus()) return course;

        enrollmentDao.save(enrollment);
        return new ResponseEntity<>(new ApiResponse<>(true, enrollment, "Student registered successfully in the course"), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllCourses(int studentId) {
        ResponseEntity<ApiResponse<?>> student = studentInterface.get(studentId);
        if(!student.getBody().isStatus()) return student;

        List<Integer> courseList = enrollmentDao.findByStudentId(studentId);
        return courseInterface.get(courseList);
    }

    public ResponseEntity<?> getAllStudents(int courseId) {
        ResponseEntity<ApiResponse<?>> course = courseInterface.get(courseId);
        if(!course.getBody().isStatus()) return course;

        List<Integer> studentList = enrollmentDao.findByCourseId(courseId);
        return studentInterface.get(studentList);
    }

    public ResponseEntity<?> deleteByCourseId(int id) {
        enrollmentDao.deleteByCourseId(id);
        return new ResponseEntity<>(new ApiResponse<>(true, null, "All enrollments for given course deleted successfully"), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteByStudentId(int id) {
        enrollmentDao.deleteByStudentId(id);
        return new ResponseEntity<>(new ApiResponse<>(true, null, "All enrollments for given student deleted successfully"), HttpStatus.OK);
    }
}
