package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.dto.OrderItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderItemAdapter {

    private final PizzaAdapter pizzaAdapter;

    public OrderItemDTO adapt(OrderItem orderItem) {
        return new OrderItemDTO(orderItem.getId(), pizzaAdapter.adapt(orderItem.getPizza()), orderItem.getAmountOfPizza());
    }
}