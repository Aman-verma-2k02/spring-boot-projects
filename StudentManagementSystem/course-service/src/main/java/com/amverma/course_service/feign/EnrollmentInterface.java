package com.amverma.course_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENROLLMENT-SERVICE", path = "/enrollment")
public interface EnrollmentInterface {
    @DeleteMapping("/internal/course/{id}")
    public ResponseEntity<?> deleteByCourseId(@PathVariable int id);
}
