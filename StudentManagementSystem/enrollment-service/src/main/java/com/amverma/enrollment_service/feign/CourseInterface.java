package com.amverma.enrollment_service.feign;

import com.amverma.enrollment_service.utility.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE", path = "/course")
public interface CourseInterface {
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<?>> get(@PathVariable int id);

    @PostMapping("/get")
    public ResponseEntity<ApiResponse<?>> get(@RequestBody List<Integer> ids);
}
