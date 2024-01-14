package com.haldovich.kafka.repository;

import com.haldovich.kafka.dto.CarCoordinates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class CarDistanceRepository {

    private static final ConcurrentHashMap<String, CarCoordinates> START_POSITION = new ConcurrentHashMap<>();

    public CarCoordinates setStartPosition(String carId, CarCoordinates carCoordinates) {
        log.info("Save start position for " + carId);
        return START_POSITION.put(carId, carCoordinates);
    }

    public CarCoordinates getStartPositionByCarId(String carId) {
        return START_POSITION.get(carId);
    }
}
