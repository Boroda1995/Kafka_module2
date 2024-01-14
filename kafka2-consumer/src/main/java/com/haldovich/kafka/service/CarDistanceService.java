package com.haldovich.kafka.service;

import com.haldovich.kafka.dto.CarCoordinates;
import com.haldovich.kafka.repository.CarDistanceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.geotools.referencing.GeodeticCalculator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CarDistanceService {

    private final GeodeticCalculator calculator;
    private final CarDistanceRepository distanceRepository;

    public double calculateCarDistance(CarCoordinates carCoordinates) {
        if (carCoordinates.carId() != null && carCoordinates.coordinates() != null
                && carCoordinates.coordinates().xAxis() != null && carCoordinates.coordinates().yAxis() != null) {
            final String carId = carCoordinates.carId();

            CarCoordinates startPosition = distanceRepository.getStartPositionByCarId(carId);
            if (startPosition == null) {
                distanceRepository.setStartPosition(carId, carCoordinates);
                return -1;
            }

            final double startX = Double.parseDouble(startPosition.coordinates().xAxis());
            final double startY = Double.parseDouble(startPosition.coordinates().yAxis());

            final double currentX = Double.parseDouble(carCoordinates.coordinates().xAxis());
            final double currentY = Double.parseDouble(carCoordinates.coordinates().yAxis());

            calculator.setStartingGeographicPoint(startX, startY);
            calculator.setDestinationGeographicPoint(currentX, currentY);
            return calculator.getOrthodromicDistance() / 1000;
        } else {
            throw new IllegalArgumentException("Error of processing car coordinates " + carCoordinates);
        }
    }
}
