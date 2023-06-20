package com.matteoveroni.awesomepizza.services;

import org.springframework.stereotype.Service;

@Service
public class OrdersService {

//    private final OrdersRepository ordersRepository;
//
//    public OrdersService(OrdersRepository ordersRepository) {
//        this.ordersRepository = ordersRepository;
//    }
//
//    public List<Order> readAllOrders() {
//        return ordersRepository.findAll();
//    }
//
//    public void registerNewOrder(Order order) {
//        ordersRepository.save(order);
//    }
//
//    @Transactional
//    public Optional<Order> getNextOrder() {
////        Optional<Order> nextOrder = ordersRepository.findOrderStateAndFirstByOrderByDateAsc(OrderState.TO_PREPARE);
////        Optional<Order> nextOrder = ordersRepository.findOrderState(OrderState.TO_PREPARE);
////        nextOrder.ifPresent(order -> order.setOrderState(OrderState.IN_PREPARATION));
////        return nextOrder;
////        return ordersRepository.findOrderState(OrderState.TO_PREPARE);
//        return Optional.empty();
//    }
//
//    @Transactional
//    public void evadeOrder(Long orderId) {
//        ordersRepository.findById(orderId).ifPresent(order -> order.setOrderState(OrderState.READY));
//    }
}
