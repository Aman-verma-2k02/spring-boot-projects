package com.amverma.order_service.controller;

import com.amverma.order_service.dto.OrderRequestDTO;
import com.amverma.order_service.dto.OrderStatusUpdateDTO;
import com.amverma.order_service.service.OrderService;
import com.amverma.order_service.util.ApiResponse;
import com.amverma.order_service.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createNewOrder(@RequestBody OrderRequestDTO orderRequestDto) {
        return ResponseEntity.ok(ApiResponse.success(orderService.createNewOrder(orderRequestDto), CommonConstants.ORDER_PLACED));
    }

    @PostMapping("/internal")
    public ResponseEntity<?> createNewOrders(@RequestBody List<OrderRequestDTO> orderRequestDTOList) {
        return ResponseEntity.ok(ApiResponse.success(orderService.createNewOrders(orderRequestDTOList), CommonConstants.ORDER_PLACED));
    }
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(ApiResponse.success(orderService.getAllOrders(), CommonConstants.ORDER_FETCHED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrderById(id), CommonConstants.ORDER_FETCHED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusUpdateDTO orderStatusUpdateDto) {
        System.out.println(orderStatusUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(orderService.updateOrderStatus(id, orderStatusUpdateDto), MessageFormat.format(CommonConstants.ORDER_STATUS_UPDATED, orderStatusUpdateDto.getStatus())));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getOrdersByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(ApiResponse.success(orderService.getOrdersByUserId(userId), CommonConstants.ORDER_FETCHED));
    }
}
