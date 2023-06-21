package com.matteoveroni.awesomepizza.factories;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PizzaFactory {

    static final String UNKNOWN_PIZZA_EX_MESSAGE = "Unknown pizza type passed!";

    public Pizza createPizza(PizzaName pizzaName) {
        Objects.requireNonNull(pizzaName, "The pizza name cannot be null!");
        return createPizza(pizzaName.name());
    }

    public Pizza createPizza(String pizzaName) {
        Objects.requireNonNull(pizzaName, "The pizza name cannot be null!");
        String type = pizzaName.trim().toLowerCase();
        return switch (type) {
            case "margherita", "pizza margherita" -> createPizzaMargherita();
            case "capricciosa", "pizza capricciosa" -> createPizzaCapricciosa();
            default -> throw new IllegalArgumentException(UNKNOWN_PIZZA_EX_MESSAGE);
        };
    }

    private Pizza createPizzaMargherita() {
        return Pizza.builder()
                .name(PizzaName.MARGHERITA.name())
                .description("Pizza con pomodoro basilico e mozzarella")
                .price(new BigDecimal("7.0"))
                .build();
    }

    private Pizza createPizzaCapricciosa() {
        return Pizza.builder()
                .name(PizzaName.CAPRICCIOSA.name())
                .description("Pizza con pomodoro, funghi, carciofini, prosciutto cotto e olive")
                .price(new BigDecimal("10.0"))
                .build();
    }
}
