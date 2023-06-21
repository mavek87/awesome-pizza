package com.matteoveroni.awesomepizza.factories;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PizzaFactoryTest {

    @ParameterizedTest
    @ValueSource(strings = {"margherita", "pizza margherita", " pizza margherita ", "MARGHERITA", "PIZZA MARGHERITA", " PIZZA MARGHERITA   ", "Pizza Margherita"})
    public void crea_pizza_margherita_ok(String pizzaName) {
        Pizza pizza = PizzaFactory.createPizza(pizzaName);

        assertEquals(PizzaName.MARGHERITA.getName(), pizza.getName());
        assertEquals(new BigDecimal("7.0"), pizza.getPrice());
    }

    @Test
    public void crea_pizza_margherita_ok_using_enum() {
        Pizza pizza = PizzaFactory.createPizza(PizzaName.MARGHERITA);

        assertEquals(PizzaName.MARGHERITA.getName(), pizza.getName());
        assertEquals(new BigDecimal("7.0"), pizza.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"capricciosa", "pizza capricciosa", " pizza capricciosa ", "CAPRICCIOSA", "PIZZA CAPRICCIOSA", " PIZZA CAPRICCIOSA   ", "Pizza Capricciosa"})
    public void crea_pizza_capricciosa_ok(String pizzaName) {
        Pizza pizza = PizzaFactory.createPizza(pizzaName);

        assertEquals(PizzaName.CAPRICCIOSA.getName(), pizza.getName());
        assertEquals(new BigDecimal("10.0"), pizza.getPrice());
    }

    @Test
    public void crea_pizza_capricciosa_ok_using_enum() {
        Pizza pizza = PizzaFactory.createPizza(PizzaName.CAPRICCIOSA);

        assertEquals(PizzaName.CAPRICCIOSA.getName(), pizza.getName());
        assertEquals(new BigDecimal("10.0"), pizza.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "xyz", " pazza pizza ", "cappicciosa", "margaritas"})
    public void crea_pizza_inesistente_throws_illegal_argument_exception(String pizzaName) {
        IllegalArgumentException ex = Assertions.assertThrows(IllegalArgumentException.class, () -> PizzaFactory.createPizza(pizzaName));

        assertEquals(PizzaFactory.UNKNOWN_PIZZA_EX_MESSAGE, ex.getMessage());
    }
}