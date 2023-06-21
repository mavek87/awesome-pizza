package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PizzaAdapter {

    private final PizzaCatalogService pizzaCatalogService;

    public PizzaDTO adaptToDTO(Pizza pizza) {
        Objects.requireNonNull(pizza, "Pizza is null!");
        return new PizzaDTO(pizza.getId(), pizza.getName(), pizza.getDescription(), pizza.getPrice());
    }

    public Pizza adaptFromDTO(PizzaDTO pizzaDTO) {
        Objects.requireNonNull(pizzaDTO, "PizzaDTO is null!");
        return pizzaCatalogService.getPizza(pizzaDTO.name()) ;
    }
}
