package com.haldovich.kafka.controller;

import com.haldovich.kafka.dto.CarCoordinates;
import com.haldovich.kafka.service.KafkaProducer;
import com.haldovich.kafka.service.impl.TaxiProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/taxi")
public class TaxiController {

    private final TaxiProducer taxiProducer;

    @PostMapping(value = "/position", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendToValid(@RequestBody CarCoordinates carCoordinates) {
        log.info("Request parameters: CarId - " + carCoordinates.carId() + " | " + carCoordinates.coordinates());
        taxiProducer.sendMessage(carCoordinates);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
