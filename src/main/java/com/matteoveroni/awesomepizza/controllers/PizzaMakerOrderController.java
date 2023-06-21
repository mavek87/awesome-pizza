package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pizza_maker/orders")
@AllArgsConstructor
@Slf4j
public class PizzaMakerOrderController {

    private final OrdersService ordersService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrderInfo(@PathVariable Long id) {
        return ResponseEntity.of(ordersService.getOrder(id));
    }

    @GetMapping(value = "next", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getNextOrderToPrepare() {
        return ResponseEntity.of(ordersService.getNextOrderToPrepare());
    }

    @PostMapping(value = "complete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> completeOrder(@PathVariable Long id) {
        return ResponseEntity.of(ordersService.completeOrder(id));
    }
}
