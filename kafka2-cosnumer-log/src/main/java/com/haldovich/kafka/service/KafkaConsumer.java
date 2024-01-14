package com.haldovich.kafka.service;

public interface KafkaConsumer<T> {
    void listenTopic(T message);
}
