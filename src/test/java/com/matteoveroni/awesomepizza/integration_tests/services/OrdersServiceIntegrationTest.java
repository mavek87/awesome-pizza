package com.matteoveroni.awesomepizza.integration_tests.services;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.adapters.OrderAdapter;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrdersServiceIntegrationTest {

    @Autowired private OrdersRepository ordersRepository;
    @Autowired private OrderAdapter orderAdapter;

    private OrdersService ordersService;

    @BeforeEach
    public void setUp() {
        ordersService = new OrdersService(ordersRepository, orderAdapter);
    }

    @AfterEach
    public void tearDown() {
        ordersRepository.deleteAll();
    }

    private final Order order1 = Order.builder()
            .id(9999L)
            .build();
    private final Order order2 = Order.builder()
            .build();

    @Test
    public void register_new_order_ok() {
        Long id = ordersService.registerNewOrder(order1);

        assertEquals(order1.getId(), id, "Error");
        assertNotEquals(9999L, id, "Error");
    }

    @Test
    public void if_no_orders_get_order_returns_empty() {
        Optional<OrderDTO> order = ordersService.getOrder(1L);

        assertTrue(order.isEmpty(), "Error, the order is not empty!");
    }

    @Test
    public void if_order_present_get_order_find_it() {
        Long id = ordersService.registerNewOrder(order1);

        OrderDTO foundOrder = ordersService.getOrder(id)
                .orElseThrow(() -> new IllegalStateException("Error, order not found!"));

        assertEquals(id, foundOrder.id(), "Error, id not expected");
        assertEquals(OrderState.TO_BE_PREPARED, foundOrder.orderState(), "Error, state not expected");
    }

    @Test
    public void multiple_order_registration_and_get_all_orders_ok() {
        ordersService.registerNewOrder(order1);
        ordersService.registerNewOrder(order2);

        List<OrderDTO> orders = ordersService.getAllOrders();

        assertEquals(2, orders.size(), "Error during multiple order registration!");
    }

    @Test
    public void get_next_order_to_prepare_is_ok() {
        ordersService.registerNewOrder(order1);
        ordersService.registerNewOrder(order2);

        OrderDTO order1DTO = ordersService.getNextOrderToPrepare().orElseThrow(() -> new IllegalStateException("Error, next order to prepare was expected"));
        assertEquals(order1.getId(), order1DTO.id());
        assertEquals(OrderState.IN_PREPARATION, order1DTO.orderState());

        OrderDTO order2DTO = ordersService.getNextOrderToPrepare().orElseThrow(() -> new IllegalStateException("Error, next order to prepare was expected"));
        assertEquals(order2.getId(), order2DTO.id());
        assertEquals(OrderState.IN_PREPARATION, order2DTO.orderState());

        Optional<OrderDTO> nextOrderToPrepare = ordersService.getNextOrderToPrepare();
        assertTrue(nextOrderToPrepare.isEmpty());
    }

    @Test
    public void complete_order_works() {
        ordersService.registerNewOrder(order1);
        assertNotEquals(OrderState.READY, order1.getOrderState());

        OrderDTO order1DTO = ordersService.completeOrder(order1.getId()).orElseThrow(() -> new IllegalStateException("Error, an order dto was expected"));
        assertEquals(OrderState.READY, order1DTO.orderState());
    }
}