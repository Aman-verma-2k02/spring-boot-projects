package com.amverma.student_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENROLLMENT-SERVICE", path = "/enrollment")
public interface EnrollmentInterface {
    @DeleteMapping("/internal/student/{id}")
    public ResponseEntity<?> deleteByStudentId(@PathVariable int id);
}
