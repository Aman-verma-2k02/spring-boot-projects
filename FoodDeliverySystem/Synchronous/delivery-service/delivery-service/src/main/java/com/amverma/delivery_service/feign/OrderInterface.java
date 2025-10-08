package com.amverma.delivery_service.feign;

import com.amverma.delivery_service.dto.OrderStatusUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ORDER-SERVICE", path = "/orders")
public interface OrderInterface {
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusUpdateDTO orderStatusUpdateDTO);
}
