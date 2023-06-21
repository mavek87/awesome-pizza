package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.services.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Order> getOrder() {
        return ordersService.getAllOrders();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.of(ordersService.getOrder(id));
    }
}
