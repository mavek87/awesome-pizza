package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderState;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    @Mock
    private OrdersRepository ordersRepository;
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        ordersService = new OrdersService(ordersRepository);
    }

    @Test
    public void get_all_orders_calls_the_repository_find_all() {
        ordersService.getAllOrders();

        verify(ordersRepository).findAll();
    }

    @Test
    public void get_order_calls_the_repository_find_by_id() {
        final Long orderId = 2L;

        ordersService.getOrder(orderId);

        verify(ordersRepository).findById(orderId);
    }

    @Test
    public void register_new_order_calls_orders_repository_save() {
        final Long savedOrderId = 1L;
        final Order savedOrder = Order.builder()
                .id(savedOrderId)
                .build();
        when(ordersRepository.save(any(Order.class))).thenReturn(savedOrder);

        Long orderId = ordersService.registerNewOrder(new Order());

        assertEquals(savedOrderId, orderId, "Error, the id is different from expectations");
        verify(ordersRepository).save(any(Order.class));
    }

    @Test
    public void get_next_order_to_prepare_calls_orders_repository_find_next_order_to_process() {
        ordersService.getNextOrderToPrepare();

        verify(ordersRepository).findNextOrderToProcess();
    }

    @Test
    public void evade_order_work_as_expected_if_order_with_this_id_exists() {
        final Long orderId = 1L;
        final Order expectedOrder = Order.builder()
                .id(orderId)
                .date(new Date())
                .notes("Just a random note")
                .orderState(OrderState.IN_PREPARATION)
                .build();

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        Optional<Order> optionalOrder = ordersService.evadeOrder(orderId);

        assertTrue(optionalOrder.isPresent(), "Error, order not found!");
        final Order order = optionalOrder.get();
        assertEquals(expectedOrder.getId(), order.getId(), "Error, order id is different from expectations");
        assertEquals(expectedOrder.getDate(), order.getDate(), "Error, order date is different from expectations");
        assertEquals(expectedOrder.getNotes(), order.getNotes(), "Error, order notes is different from expectations");
        assertEquals(OrderState.READY, order.getOrderState(), "Error, order state is different from expectations");
    }

}