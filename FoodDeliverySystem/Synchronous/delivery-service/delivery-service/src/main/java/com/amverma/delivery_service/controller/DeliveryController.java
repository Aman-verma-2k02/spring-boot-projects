package com.amverma.delivery_service.controller;

import com.amverma.delivery_service.dto.DeliveryRequestDTO;
import com.amverma.delivery_service.dto.DeliveryStatusUpdateDTO;
import com.amverma.delivery_service.service.DeliveryService;
import com.amverma.delivery_service.util.ApiResponse;
import com.amverma.delivery_service.util.CommonConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    public final DeliveryService deliveryService;
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createNewDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.createNewDelivery(deliveryRequestDTO), CommonConstants.DELIVERY_CREATED));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllDeliveries() {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.getAllDeliveries(), CommonConstants.DELIVERY_FETCHED));
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<?>> getAllPendingDeliveries() {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.getAllPendingDeliveries(), CommonConstants.DELIVERY_FETCHED));
    }

    @PatchMapping("/{id}/assign/{partnerId}")
    public ResponseEntity<ApiResponse<?>> assignDeliveryPartnerToDelivery(@PathVariable int id, @PathVariable int partnerId) {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.assignDeliveryPartnerToDelivery(id, partnerId), CommonConstants.DELIVERY_PARTNER_ASSIGNED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateDeliveryStatus(@PathVariable int id, @RequestBody DeliveryStatusUpdateDTO deliveryStatusUpdateDTO) {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.updateDeliveryStatus(id, deliveryStatusUpdateDTO),
                MessageFormat.format(CommonConstants.DELIVERY_STATUS_UPDATED, deliveryStatusUpdateDTO.getStatus())));
    }

    @PutMapping("/order/{orderId}/cancel")
    public ResponseEntity<ApiResponse<?>> cancelDelivery(@PathVariable int orderId) {
        return ResponseEntity.ok(ApiResponse.success(deliveryService.cancelDelivery(orderId), CommonConstants.DELIVERY_CANCELLED));
    }
}
