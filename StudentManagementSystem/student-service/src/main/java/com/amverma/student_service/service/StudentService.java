package com.amverma.student_service.service;

import com.amverma.student_service.dao.StudentDao;
import com.amverma.student_service.feign.EnrollmentInterface;
import com.amverma.student_service.model.Student;
import com.amverma.student_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    EnrollmentInterface enrollmentInterface;

    public ResponseEntity<?> add(Student student) {
        studentDao.save(student);
        return new ResponseEntity<>(new ApiResponse<>(true, student, "User added successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> update(int id, Student student) {
        Student s = studentDao.findById(id).orElse(null);
        if(s == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Student doesn't exist!!!"), HttpStatus.OK);

        if(student.getName() != null) s.setName(s.getName());
        if(student.getEmail() != null) s.setEmail(student.getEmail());
        if(student.getDepartment() != null) s.setDepartment(student.getDepartment());
        studentDao.save(s);
        return new ResponseEntity<>(new ApiResponse<>(true, s, "Student details updated successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(int id) {
        Student s = studentDao.findById(id).orElse(null);
        if(s == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Student doesn't exist!!!"), HttpStatus.OK);

        studentDao.deleteById(id);
        enrollmentInterface.deleteByStudentId(id);
        return new ResponseEntity<>(new ApiResponse<>(true, s, "Student deleted successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> get(int id) {
        Student s = studentDao.findById(id).orElse(null);
        if(s == null) return new ResponseEntity<>(new ApiResponse<>(false, null, "Student doesn't exist!!!"), HttpStatus.OK);

        return new ResponseEntity<>(new ApiResponse<>(true, s, "Student details got successfully"), HttpStatus.OK);
    }

    public ResponseEntity<?> get(List<Integer> ids) {
        List<Student> students = new ArrayList<>();
        for(int id: ids) {
            studentDao.findById(id).ifPresent(students::add);
        }
        return new ResponseEntity<>(new ApiResponse<>(true, students, "Student details got successfully"), HttpStatus.OK);
    }
}
