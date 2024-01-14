package com.haldovich.kafka.service.impl;

import com.haldovich.kafka.dto.CarCoordinates;
import com.haldovich.kafka.dto.CarDistance;
import com.haldovich.kafka.service.CarDistanceService;
import com.haldovich.kafka.service.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer<CarCoordinates> {

    @Value("${kafka.topic.car-distance}")
    private String outputTopic;
    private final CarDistanceService carDistanceService;
    private final KafkaTemplate<String, CarDistance> kafkaTemplate;

    @KafkaListener(topics = "${kafka.topic.car-location}", containerFactory = "listenerContainerFactory")
    public void listenTopic(CarCoordinates message) {
        log.info("Message from broken: " + message);
        double distance = carDistanceService.calculateCarDistance(message);

        if (distance == -1) {
            return;
        }

        CarDistance carDistance = CarDistance.builder().carId(message.carId()).distance(distance).build();
        System.out.println(carDistance);
        kafkaTemplate.send(outputTopic, message.carId(), carDistance);
        log.info("Pushed message to output topic: " + carDistance);
    }
}
