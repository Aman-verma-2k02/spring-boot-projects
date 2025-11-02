package com.kafka.testing.kafka_demo.producer;

import com.kafka.testing.kafka_demo.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public void publish(Order order) {
        logger.info(MessageFormat.format("New {0} published to topic", order));

//        Both will work same either we create Message object for kafka template or directly pass the values to template.
//        kafkaTemplate.send("order-topic", order);

        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
