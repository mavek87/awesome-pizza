package com.matteoveroni.awesomepizza.integration_tests;

import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PizzaCatalogServiceIntegrationTest {

    @Autowired private PizzaCatalogService pizzaCatalogService;

    @Test
    public void get_pizza_catalog() {
        List<PizzaDTO> pizzaCatalog = pizzaCatalogService.getPizzaCatalog();

        assertEquals(2, pizzaCatalog.size(), "Error, the number of pizzas in the catalog is different from expectations");
    }

    @Test
    public void get_pizza_margherita_ok() {
        Pizza pizza = pizzaCatalogService.getPizza(PizzaName.MARGHERITA);

        assertEquals(PizzaName.MARGHERITA.name(), pizza.getName(), "Error, this is not the expected pizza (" + PizzaName.MARGHERITA.name() + ")");
    }

    @Test
    public void get_pizza_margherita_by_name_ok() {
        Pizza pizza = pizzaCatalogService.getPizza("margherita");

        assertEquals(PizzaName.MARGHERITA.name(), pizza.getName(), "Error, this is not the expected pizza (" + PizzaName.MARGHERITA.name() + ")");
    }

    @Test
    public void get_pizza_capricciosa_ok() {
        Pizza pizza = pizzaCatalogService.getPizza(PizzaName.CAPRICCIOSA);

        assertEquals(PizzaName.CAPRICCIOSA.name(), pizza.getName(), "Error, this is not the expected pizza (" + PizzaName.CAPRICCIOSA.name() + ")");
    }

    @Test
    public void get_pizza_capricciosa_by_name_ok() {
        Pizza pizza = pizzaCatalogService.getPizza("capricciosa");

        assertEquals(PizzaName.CAPRICCIOSA.name(), pizza.getName(), "Error, this is not the expected pizza (" + PizzaName.CAPRICCIOSA.name() + ")");
    }

    @Test
    public void get_unknown_pizza_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> pizzaCatalogService.getPizza("Nome pizza inesistente."));
    }
}