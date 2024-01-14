package com.haldovich.kafka.service.impl;

import com.haldovich.kafka.dto.CarDistance;
import com.haldovich.kafka.service.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer<CarDistance> {

    @KafkaListener(topics = "${kafka.topic.car-distance}", containerFactory = "listenerContainerFactory")
    public void listenTopic(CarDistance message) {
        log.info("Car: " + message.carId() + "Distance(km): " + message.distance());
    }
}
