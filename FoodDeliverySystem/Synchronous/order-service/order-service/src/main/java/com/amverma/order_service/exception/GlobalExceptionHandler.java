package com.amverma.order_service.exception;

import com.amverma.order_service.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFound(OrderNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(OrderItemNotFoundException.class)
    public ResponseEntity<?> handleOrderItemNotFound(OrderItemNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleArgTypeMismatch(Exception exception) {
        return ResponseEntity.ok(ApiResponse.failure("Error: Argument type mismatch exception"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleRestaurantNotFound(RestaurantNotFoundException exception) {
        return ResponseEntity.ok(ApiResponse.failure(exception.getMessage()));
    }
}
