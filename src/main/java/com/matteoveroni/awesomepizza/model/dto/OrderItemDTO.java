package com.matteoveroni.awesomepizza.model.dto;

public record OrderItemDTO (Long id, PizzaDTO pizza, Integer amountOfPizza) {
}