package com.haldovich.kafka.controller;

import com.haldovich.kafka.dto.JsonMessage;
import com.haldovich.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final KafkaProducer kafkaListener;

    @PostMapping(value = "/topic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendToValid(@RequestBody JsonMessage message) {
        kafkaListener.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
