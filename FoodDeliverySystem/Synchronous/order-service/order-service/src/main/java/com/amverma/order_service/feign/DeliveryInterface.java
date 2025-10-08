package com.amverma.order_service.feign;

import com.amverma.order_service.dto.DeliveryRequestDTO;
import com.amverma.order_service.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "DELIVERY-SERVICE", path = "/deliveries")
public interface DeliveryInterface {
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createNewDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO);

    @PutMapping("/order/{orderId}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelDelivery(@PathVariable int orderId);
}
