package com.matteoveroni.awesomepizza.services;

import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

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

}