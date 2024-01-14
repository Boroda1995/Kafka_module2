package com.haldovich.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CarCoordinates(@JsonProperty("carId") String carId, @JsonProperty("coordinates") Coordinates coordinates) {
}
