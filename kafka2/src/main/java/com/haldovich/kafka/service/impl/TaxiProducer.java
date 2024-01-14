package com.haldovich.kafka.service.impl;

import com.haldovich.kafka.dto.CarCoordinates;
import com.haldovich.kafka.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaxiProducer implements KafkaProducer<CarCoordinates> {

    private final String topicName;
    private final KafkaTemplate<String, CarCoordinates> kafkaTemplate;

    public TaxiProducer(@Value("${kafka.topic.car_location}") String topicName,
                        KafkaTemplate<String, CarCoordinates> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(CarCoordinates msg) {
        log.info("Push record to topic: " + topicName + " with key: " + msg.carId());
        kafkaTemplate.send(topicName, msg.carId(), msg);
    }
}
