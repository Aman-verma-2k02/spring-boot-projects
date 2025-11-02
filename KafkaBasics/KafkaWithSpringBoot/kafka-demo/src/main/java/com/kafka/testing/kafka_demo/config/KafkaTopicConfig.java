package com.kafka.testing.kafka_demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateOrderTopic() {
        return TopicBuilder
                .name("order-topic")
                .partitions(4)
                .replicas(2)
                .build();
    }
}
