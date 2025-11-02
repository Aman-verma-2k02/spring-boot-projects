package com.kafka.testing.kafka_demo.controller;

import com.kafka.testing.kafka_demo.dto.Order;
import com.kafka.testing.kafka_demo.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/orders")
public class OrderController {
    private OrderProducer orderProducer;
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public ResponseEntity<?> publishOrder(@RequestBody Order order) {
        orderProducer.publish(order);
        return ResponseEntity.ok("Order placed successfully");
    }
}
