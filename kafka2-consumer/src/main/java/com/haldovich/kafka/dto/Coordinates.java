package com.haldovich.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Coordinates(@JsonProperty("x-axis") String xAxis, @JsonProperty("y-axis") String yAxis) {
}
