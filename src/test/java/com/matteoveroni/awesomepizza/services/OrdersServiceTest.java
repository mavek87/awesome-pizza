package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.adapters.OrderAdapter;
import com.matteoveroni.awesomepizza.model.dto.OrderDTO;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    @Mock private OrdersRepository ordersRepository;
    @Mock private OrderAdapter orderAdapter;
    private OrdersService ordersService;

    private final Order order1 = Order.builder()
            .id(1L)
            .build();
    private final Order order2 = Order.builder()
            .id(2L)
            .build();
    private final OrderDTO order1DTO = new OrderDTO(order1.getId(), null, null, null, null);
    private final OrderDTO order2DTO = new OrderDTO(order2.getId(), null, null, null, null);

    @BeforeEach
    void setUp() {
        ordersService = new OrdersService(ordersRepository, orderAdapter);
    }

    @Test
    public void get_all_orders_with_no_orders_found() {
        when(ordersRepository.findAll()).thenReturn(new ArrayList<>());

        List<OrderDTO> orders = ordersService.getAllOrders();

        verify(ordersRepository, times(1)).findAll();
        verify(orderAdapter, never()).adaptToDTO(any(Order.class));
        assertTrue(orders.isEmpty(), "Error, the order list is not empty!");
    }

    @Test
    public void get_all_orders_with_some_orders_found() {
        when(ordersRepository.findAll()).thenReturn(List.of(order1, order2));
        when(orderAdapter.adaptToDTO(order1)).thenReturn(order1DTO);
        when(orderAdapter.adaptToDTO(order2)).thenReturn(order2DTO);

        List<OrderDTO> orders = ordersService.getAllOrders();

        verify(ordersRepository, times(1)).findAll();
        verify(orderAdapter, times(2)).adaptToDTO(any(Order.class));
        assertEquals(2, orders.size(), "Error, the order list size is different from expectations!");
    }

    @Test
    public void get_order_doesnt_find_order() {
        final Long id = order1.getId();
        when(ordersRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Optional<OrderDTO> order = ordersService.getOrder(id);

        verify(ordersRepository, times(1)).findById(id);
        verify(orderAdapter, never()).adaptToDTO(order1);
        assertTrue(order.isEmpty(), "Error, the order is not empty as expected!");
    }

    @Test
    public void get_order_finds_order() {
        final Long id = order1.getId();
        when(ordersRepository.findById(id)).thenReturn(Optional.of(order1));
        when(orderAdapter.adaptToDTO(order1)).thenReturn(order1DTO);

        Optional<OrderDTO> order = ordersService.getOrder(id);

        verify(ordersRepository, times(1)).findById(id);
        verify(orderAdapter, times(1)).adaptToDTO(order1);
        assertEquals(order1DTO, order.get(), "Error, the order is not the one expected!");
    }

    @Test
    public void get_order_state_is_ok() {
        final Long id = order1.getId();
        final Order order = Order.builder()
                .id(id)
                .orderState(OrderState.READY)
                .build();
        when(ordersRepository.findById(id)).thenReturn(Optional.of(order));

        Optional<OrderState> optOrderState = ordersService.getOrderState(id);

        assertEquals(order.getOrderState(), optOrderState.get(), "Error, the state is different from the expectations!");
    }

    @Test
    public void register_new_order_works() {
        final Long id = order1.getId();
        when(ordersRepository.save(order1)).thenAnswer(answer -> {
            order1.setId(id);
            return order1;
        });

        Long orderId = ordersService.registerNewOrder(order1);

        verify(ordersRepository, times(1)).save(order1);
        assertEquals(id, orderId, "Error, the id is different from expectations");
    }

    @Test
    public void get_next_order_to_prepare_is_ok() {
        when(ordersRepository.findNextOrderToProcess()).thenReturn(Optional.of(order1));
        when(orderAdapter.adaptToDTO(order1)).thenReturn(order1DTO);

        Optional<OrderDTO> nextOrderToPrepare = ordersService.getNextOrderToPrepare();

        verify(ordersRepository, times(1)).findNextOrderToProcess();
        verify(ordersRepository, times(1)).save(order1);
        assertEquals(order1DTO, nextOrderToPrepare.get(), "Error, the next order to prepare is different from expectations");
    }

    @Test
    public void get_next_order_to_prepare_with_any_other_orders_left_is_ok() {
        when(ordersRepository.findNextOrderToProcess()).thenReturn(Optional.empty());

        Optional<OrderDTO> nextOrderToPrepare = ordersService.getNextOrderToPrepare();

        verify(ordersRepository, times(1)).findNextOrderToProcess();
        verify(ordersRepository, never()).save(any(Order.class));
        assertTrue(nextOrderToPrepare.isEmpty(), "Error, the next order to prepare is not empty");
    }

    @Test
    public void complete_order_work_as_expected_if_order_with_this_id_exists() {
        final Long id = order1.getId();
        when(ordersRepository.findById(id)).thenReturn(Optional.of(order1));
        when(orderAdapter.adaptToDTO(order1)).thenAnswer(answer -> new OrderDTO(order1DTO.id(), null, OrderState.READY, null, null));

        Optional<OrderDTO> optionalOrder = ordersService.completeOrder(id);

        final OrderDTO orderDTO = optionalOrder.get();
        assertEquals(order1DTO.id(), orderDTO.id(), "Error, order id is different from expectations");
        assertEquals(OrderState.READY, orderDTO.orderState(), "Error, order state is different from expectations");
    }
}