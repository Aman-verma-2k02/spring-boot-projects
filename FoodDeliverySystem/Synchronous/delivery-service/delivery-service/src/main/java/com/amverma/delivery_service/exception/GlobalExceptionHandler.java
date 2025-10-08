package com.amverma.delivery_service.exception;

import com.amverma.delivery_service.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DeliveryPartnerNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleDeliveryPartnerNotFound(DeliveryPartnerNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(DeliveryNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleDeliveryNotFound(DeliveryNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(DeliveryPartnerAssignmentFailedException.class)
    public ResponseEntity<ApiResponse<?>> handleDeliveryPartnerAssign(DeliveryPartnerAssignmentFailedException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }
}
