package com.haldovich.kafka.service;

import com.haldovich.kafka.dto.JsonMessage;

public interface KafkaProducer {
    void sendMessage(JsonMessage msg);
}
