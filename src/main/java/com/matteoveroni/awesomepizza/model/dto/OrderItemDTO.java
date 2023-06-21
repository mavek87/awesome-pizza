package com.matteoveroni.awesomepizza.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItemDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("pizza") PizzaDTO pizza,
        @JsonProperty("amount_of_pizza") Integer amountOfPizza) {
}