package com.matteoveroni.awesomepizza.config;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.enums.OrderState;
import com.matteoveroni.awesomepizza.model.enums.PizzaName;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InitDataConfiguration {

    @Bean
    CommandLineRunner initData(OrdersRepository ordersRepository, PizzaCatalogService pizzaCatalogService) {
        return args -> {
            final Pizza pizzaMargherita = pizzaCatalogService.getPizza(PizzaName.MARGHERITA);
            final Pizza pizzaCapricciosa = pizzaCatalogService.getPizza(PizzaName.CAPRICCIOSA);

            final OrderItem item1Order1 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(1)
                    .build();

            final OrderItem item2Order1 = OrderItem.builder()
                    .pizza(pizzaCapricciosa)
                    .amountOfPizza(2)
                    .build();

            final OrderItem item3Order1 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(1)
                    .build();

            final Order order1 = Order.builder()
                    .date(new Date())
                    .orderState(OrderState.TO_BE_PREPARED)
                    .orderItems(List.of(item1Order1, item2Order1, item3Order1))
                    .build();

            final OrderItem item1Order2 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(1)
                    .build();

            final Order order2 = Order.builder()
                    .date(new Date())
                    .orderState(OrderState.TO_BE_PREPARED)
                    .orderItems(List.of(item1Order2))
                    .build();

            ordersRepository.saveAll(List.of(order1, order2));
        };
    }
}
