package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrder() {
        return ordersService.getAllOrders();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
//        ordersService.registerNewOrder()
        return ResponseEntity.of(ordersService.getOrder(id));
    }

//    @PostMapping(
//            value = "/register_order",
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    public void Long registerNewOrder(@RequestBody Order orderDTO) {
//        return 1L;
//    }
}
