package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import org.springframework.stereotype.Component;

@Component
public class PizzaAdapter {

    public PizzaDTO adapt(Pizza pizza) {
        return new PizzaDTO(pizza.getId(), pizza.getName(), pizza.getDescription(), pizza.getPrice());
    }
}
