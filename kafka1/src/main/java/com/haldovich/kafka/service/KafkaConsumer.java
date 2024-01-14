package com.haldovich.kafka.service;

import com.haldovich.kafka.dto.JsonMessage;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

public interface KafkaConsumer {
    void listenTopic(JsonMessage message);
    ConcurrentHashMap<LocalDate, JsonMessage> getConsumedMessages();
}
