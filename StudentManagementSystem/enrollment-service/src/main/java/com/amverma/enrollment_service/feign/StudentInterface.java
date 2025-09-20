package com.amverma.enrollment_service.feign;

import com.amverma.enrollment_service.utility.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE", path = "/student")
public interface StudentInterface {
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<?>> get(@PathVariable int id);

    @PostMapping("/get")
    public ResponseEntity<?> get(@RequestBody List<Integer> ids);
}
