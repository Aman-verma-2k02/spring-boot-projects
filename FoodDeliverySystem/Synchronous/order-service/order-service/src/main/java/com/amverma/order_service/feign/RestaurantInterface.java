package com.amverma.order_service.feign;

import com.amverma.order_service.dto.RestaurantResponseDTO;
import com.amverma.order_service.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RESTAURANT-SERVICE", path = "/restaurants")
public interface RestaurantInterface {
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantResponseDTO>> getRestaurantById(@PathVariable int id);
}
