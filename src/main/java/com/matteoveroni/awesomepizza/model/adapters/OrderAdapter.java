package com.matteoveroni.awesomepizza.model.adapters;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.dto.OrderItemDTO;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderAdapter {

    private final OrderItemAdapter orderItemAdapter;

    public OrderDTO adaptToDTO(Order order) {
        Objects.requireNonNull(order);
        List<OrderItemDTO> orderItemDTOList = order.getOrderItems().stream()
                .map(orderItemAdapter::adaptToDTO)
                .toList();
        return new OrderDTO(order.getId(), orderItemDTOList, order.getOrderState(), order.getDate(), order.getNotes());
    }

    public Order adaptFromDTO(OrderDTO orderDTO) {
        Objects.requireNonNull(orderDTO);
        List<OrderItem> orderItemList = orderDTO.orderItems().stream()
                .map(orderItemAdapter::adaptFromDTO)
                .toList();
        return new Order(orderDTO.id(), orderItemList, orderDTO.orderState(), orderDTO.date(), orderDTO.notes());
    }
}
