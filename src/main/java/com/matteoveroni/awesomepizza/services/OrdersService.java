package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.adapters.OrderAdapter;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrderAdapter orderAdapter;

    public List<OrderDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(orderAdapter::adaptToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrder(Long id) {
        return ordersRepository
                .findById(id)
                .map(orderAdapter::adaptToDTO);
    }

    public Optional<OrderState> getOrderState(Long id) {
        return ordersRepository
                .findById(id)
                .map(Order::getOrderState);
    }

    public Long registerNewOrder(Order order) {
        order.setId(null);
        order.setDate(new Date());
        order.setOrderState(OrderState.TO_BE_PREPARED);
        Order registeredOrder = ordersRepository.save(order);
        return registeredOrder.getId();
    }

    public Optional<OrderDTO> getNextOrderToPrepare() {
        Optional<Order> nextOrderToProcess = ordersRepository.findNextOrderToProcess();
        nextOrderToProcess.ifPresent(order -> {
            order.setOrderState(OrderState.IN_PREPARATION);
            ordersRepository.save(order);
        });
        return nextOrderToProcess.map(orderAdapter::adaptToDTO);
    }

    public Optional<OrderDTO> completeOrder(Long orderId) {
        Optional<Order> orderToEvade = ordersRepository.findById(orderId);
        orderToEvade.ifPresent(order -> order.setOrderState(OrderState.READY));
        return orderToEvade.map(orderAdapter::adaptToDTO);
    }
}
