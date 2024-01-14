package com.haldovich.kafka.service.impl;

import com.haldovich.kafka.dto.JsonMessage;
import com.haldovich.kafka.service.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class KafkaConsumerImpl implements KafkaConsumer {

    private static final ConcurrentHashMap<LocalDate, JsonMessage> LIST_OF_MESSAGES = new ConcurrentHashMap<>();

    @KafkaListener(topics = "${spring.kafka.topic_name}", containerFactory = "listenerContainerFactory")
    public void listenTopic(JsonMessage message) {
        LIST_OF_MESSAGES.put(LocalDate.now(), message);
        log.info("Message from broken: " + message);
    }

    public ConcurrentHashMap<LocalDate, JsonMessage> getConsumedMessages() {
        return LIST_OF_MESSAGES;
    }

}
