package com.haldovich.kafka.service;

public interface KafkaProducer<T> {
    void sendMessage(T msg);
}
