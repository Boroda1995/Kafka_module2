package com.haldovich.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonMessage(@JsonProperty("product") String product,
                   @JsonProperty("price") String price) {
}
