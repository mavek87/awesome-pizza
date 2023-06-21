package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.dto.OrderItemDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderAdapter {

    private final OrderItemAdapter orderItemAdapter;

    public OrderDTO adapt(Order order) {
        List<OrderItemDTO> orderItemDTOList = order.getOrderItems().stream()
                .map(orderItemAdapter::adapt)
                .collect(Collectors.toList());
        return new OrderDTO(order.getId(), orderItemDTOList, order.getOrderState(), order.getDate(), order.getNotes());
    }
}
