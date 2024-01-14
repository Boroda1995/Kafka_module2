package com.haldovich.kafka.service.impl;

import com.haldovich.kafka.dto.JsonMessage;
import com.haldovich.kafka.dto.MessageDto;
import com.haldovich.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

    @Value("${spring.kafka.topic_name}")
    private String topicName;

    @Autowired
    private final KafkaTemplate<String, JsonMessage> kafkaTemplate;

    @Override
    public void sendMessage(JsonMessage msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
