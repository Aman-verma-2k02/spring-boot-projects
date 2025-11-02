package com.kafka.testing.kafka_demo.consumer;

import com.kafka.testing.kafka_demo.dto.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class OrderConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

//    @KafkaListener(topics = "order-topic", groupId = "my-group")
//    public void consume(Order record) {
//        logger.info(MessageFormat.format("{0} consumed from kafka topic", record));
//    }

    //Both will work fine, only difference is the key value which was sent was kafka template
    //It will also display the key used to send message via kafka template
    @KafkaListener(topics = "order-topic", groupId = "my-group")
    public void consume(ConsumerRecord<String, Order> record) {
        logger.info(MessageFormat.format("{0} consumed from kafka topic", record.value()));
        System.out.println("Key: " + record.key() + ", Value: " + record.value());
    }
}
