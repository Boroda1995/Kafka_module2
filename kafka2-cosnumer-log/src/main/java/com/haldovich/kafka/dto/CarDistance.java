package com.haldovich.kafka.dto;

import lombok.Builder;

@Builder
public record CarDistance(String carId, double distance) {
}
