package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class PizzaAdapter {

    public PizzaDTO adaptToDTO(Pizza pizza) {
        Objects.requireNonNull(pizza);
        return new PizzaDTO(pizza.getId(), pizza.getName(), pizza.getDescription(), pizza.getPrice());
    }

    public Pizza adaptFromDTO(PizzaDTO pizzaDTO) {
        Objects.requireNonNull(pizzaDTO);
        return new Pizza(pizzaDTO.id(), pizzaDTO.name(), pizzaDTO.description(), pizzaDTO.price(), new ArrayList<>());
    }
}
