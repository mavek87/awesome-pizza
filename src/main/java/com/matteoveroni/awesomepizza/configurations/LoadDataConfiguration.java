package com.matteoveroni.awesomepizza.configurations;

import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import com.matteoveroni.awesomepizza.services.OrdersService;
import com.matteoveroni.awesomepizza.services.PizzaCatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDataConfiguration {

    private static final Logger log = LoggerFactory.getLogger(LoadDataConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(OrdersRepository ordersRepository, OrdersService ordersService, PizzaCatalogService pizzaService) {

        return args -> {

//            Pizza pizzaMargherita = PizzaFactory.createPizza(PizzaName.MARGHERITA);
//            Pizza pizzaCapricciosa = PizzaFactory.createPizza(PizzaName.CAPRICCIOSA);

//            OrderItem item1Order1 = OrderItem.builder()
//                    .pizza(pizzaService.getPizza(PizzaName.MARGHERITA))
//                    .amountOfPizza(1)
//                    .build();
////
//            OrderItem item2Order1 = OrderItem.builder()
//                    .pizza(pizzaService.getPizza(PizzaName.CAPRICCIOSA))
//                    .amountOfPizza(1)
//                    .build();
//
//            OrderItem item3Order1 = OrderItem.builder()
//                    .pizza(pizzaService.getPizza(PizzaName.MARGHERITA))
//                    .amountOfPizza(1)
//                    .build();
//
//            Order order1 = Order.builder()
//                    .date(new Date())
//                    .orderState(OrderState.TO_BE_PREPARED)
//                    .orderItems(List.of(item1Order1, item2Order1, item3Order1))
//                    .build();
//
//            OrderItem item1Order2 = OrderItem.builder()
//                    .pizza(pizzaService.getPizza(PizzaName.MARGHERITA))
//                    .amountOfPizza(1)
//                    .build();
//
//            Thread.sleep(200);
//
//            Order order2 = Order.builder()
//                    .date(new Date())
//                    .orderState(OrderState.TO_BE_PREPARED)
//                    .orderItems(List.of(item1Order2))
//                    .build();
//
//            ordersRepository.saveAll(List.of(order1, order2));
//
//            Optional<Order> nextOrderToProcess = ordersRepository.findNextOrderToProcess();
//            nextOrderToProcess.ifPresent(order -> log.info("order id: {}, date: {}", order.getId(), order.getDate()));
//
//
//            Optional<OrderDTO> nextOrderToPrepare = ordersService.getNextOrderToPrepare();
//            log.info("nextOrderToPrepare: {}", nextOrderToPrepare);
//
//
//            List<Order> all = ordersRepository.findAll();
//
//            log.info("all => " + all);
//
//            List<OrderDTO> allOrders = ordersService.getAllOrders();
//            log.info("allOrders => " + allOrders);
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String s = objectMapper.writeValueAsString(allOrders);
//
//            log.info("s: {}", s);
        };
    }
}
