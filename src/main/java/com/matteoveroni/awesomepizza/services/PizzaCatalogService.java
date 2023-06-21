package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.factories.PizzaFactory;
import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import com.matteoveroni.awesomepizza.repositories.PizzaRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class PizzaCatalogService {

    private final PizzaRepository pizzaRepository;
    private final Map<PizzaName, Pizza> pizzaCatalog = new HashMap<>();

    public PizzaCatalogService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
        for (PizzaName pizzaName : PizzaName.values()) {
            Pizza pizza = PizzaFactory.createPizza(pizzaName);
            pizzaCatalog.put(pizzaName, pizza);
            pizzaRepository.save(pizza);
        }
    }

    public Pizza getPizza(PizzaName pizzaName) {
        Objects.requireNonNull(pizzaName);
        return pizzaCatalog.get(pizzaName);
    }

    public Pizza getPizza(String pizzaName) {
        Objects.requireNonNull(pizzaName);
        pizzaName = pizzaName.trim().toUpperCase();
        return pizzaCatalog.get(PizzaName.valueOf(pizzaName));
    }
}
