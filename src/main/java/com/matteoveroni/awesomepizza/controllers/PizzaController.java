package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.model.dto.PizzaDTO;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pizza_catalog")
@AllArgsConstructor
@Slf4j
public class PizzaController {

    private final PizzaCatalogService pizzaCatalogService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PizzaDTO> getPizzaCatalog() {
        return pizzaCatalogService.getPizzaCatalog();
    }
}
