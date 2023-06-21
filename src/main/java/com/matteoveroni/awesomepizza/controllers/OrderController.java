package com.matteoveroni.awesomepizza.controllers;

import com.matteoveroni.awesomepizza.exceptions.MalformedOrderException;
import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.adapters.OrderAdapter;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {

    private final OrdersService ordersService;
    private final OrderAdapter orderAdapter;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getOrder() {
        return ordersService.getAllOrders();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.of(ordersService.getOrder(id));
    }

    @PostMapping(
//            value = "/x",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Long registerNewOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderAdapter.adaptFromDTO(orderDTO);
            return ordersService.registerNewOrder(order);
        } catch (NullPointerException ex) {
            log.error("Error", ex);
            throw new MalformedOrderException();
        } catch (Exception ex) {
            log.error("Error", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ops.. something went wrong");
        }
    }
}
