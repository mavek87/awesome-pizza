package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderState;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Optional<Order> getOrder(Long id) {
        return ordersRepository.findById(id);
    }

    public Long registerNewOrder(Order order) {
        order.setId(null);
        order.setDate(new Date());
        order.setOrderState(OrderState.TO_BE_PREPARED);
        Order registeredOrder = ordersRepository.save(order);
        return registeredOrder.getId();
    }

    public Optional<Order> getNextOrderToPrepare() {
        Optional<Order> nextOrderToProcess = ordersRepository.findNextOrderToProcess();
        nextOrderToProcess.ifPresent(order -> {
            order.setOrderState(OrderState.IN_PREPARATION);
            ordersRepository.save(order);
        });
        return nextOrderToProcess;
    }

    public Optional<Order> evadeOrder(Long orderId) {
        Optional<Order> orderToEvade = ordersRepository.findById(orderId);
        orderToEvade.ifPresent(order -> order.setOrderState(OrderState.READY));
        return orderToEvade;
    }
}
