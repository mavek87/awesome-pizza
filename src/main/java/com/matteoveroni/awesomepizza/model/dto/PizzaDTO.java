package com.matteoveroni.awesomepizza.model.dto;

import java.math.BigDecimal;

public record PizzaDTO (Long id, String name, String description, BigDecimal price){
}