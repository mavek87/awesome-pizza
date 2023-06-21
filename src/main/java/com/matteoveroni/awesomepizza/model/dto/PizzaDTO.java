package com.matteoveroni.awesomepizza.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record PizzaDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("price") BigDecimal price){
}