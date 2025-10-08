package com.amverma.restaurant_service.exception;

import com.amverma.restaurant_service.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleRestaurantNotFound(RestaurantNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleMenuItemNotFound(MenuItemNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }
}
