package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.dto.OrderItemDTO;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderItemAdapter {

    private final PizzaAdapter pizzaAdapter;

    public OrderItemDTO adaptToDTO(OrderItem orderItem) {
        Objects.requireNonNull(orderItem);
        return new OrderItemDTO(orderItem.getId(), pizzaAdapter.adaptToDTO(orderItem.getPizza()), orderItem.getAmountOfPizza());
    }

    public OrderItem adaptFromDTO(OrderItemDTO orderItemDTO) {
        Objects.requireNonNull(orderItemDTO);
        return new OrderItem(orderItemDTO.id(), pizzaAdapter.adaptFromDTO(orderItemDTO.pizza()), orderItemDTO.amountOfPizza());
    }
}