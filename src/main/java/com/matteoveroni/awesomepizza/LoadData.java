package com.matteoveroni.awesomepizza;

import com.matteoveroni.awesomepizza.factories.PizzaFactory;
import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.OrderState;
import com.matteoveroni.awesomepizza.model.Pizza;
import com.matteoveroni.awesomepizza.model.PizzaName;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;
import com.matteoveroni.awesomepizza.services.OrdersService;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

//    public static SessionFactory getCurrentSessionFromJPA() {
//        // JPA and Hibernate SessionFactory example
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = emf.createEntityManager();
//        // Get the Hibernate Session from the EntityManager in JPA
//        Session session = entityManager.unwrap(org.hibernate.Session.class);
//        return session.getSessionFactory();
//    }

    @Bean
    CommandLineRunner initDatabase(OrdersRepository ordersRepository, OrdersService ordersService) {

        return args -> {

            Pizza pizzaMargherita = PizzaFactory.createPizza(PizzaName.MARGHERITA);
            Pizza pizzaCapricciosa = PizzaFactory.createPizza(PizzaName.CAPRICCIOSA);

            OrderItem item1Order1 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(3)
                    .build();

            OrderItem item2Order1 = OrderItem.builder()
                    .pizza(pizzaCapricciosa)
                    .amountOfPizza(1)
                    .build();

            OrderItem item3Order1 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(1)
                    .build();

            Order order1 = Order.builder()
                    .date(new Date())
                    .orderState(OrderState.TO_PREPARE)
                    .orderItems(List.of(item1Order1, item2Order1, item3Order1))
                    .build();

            OrderItem item1Order2 = OrderItem.builder()
                    .pizza(pizzaMargherita)
                    .amountOfPizza(1)
                    .build();

            Thread.sleep(200);

            Order order2 = Order.builder()
                    .date(new Date())
                    .orderState(OrderState.TO_PREPARE)
                    .orderItems(List.of(item1Order2))
                    .build();

            ordersRepository.saveAll(List.of(order1, order2));

            Optional<Order> nextOrderToProcess = ordersRepository.findNextOrderToProcess();
            nextOrderToProcess.ifPresent(order -> log.info("order id: {}, date: {}", order.getId(), order.getDate()));

//
//
//            List<Order> all = ordersRepository.findAll();
//
//
//
//            log.info("all => " + all);

//            log.info("Preloading " + ordersRepository.save(new Employee("Bilbo Baggins", "burglar")));
//            log.info("Preloading " + ordersRepository.save(new Employee("Frodo Baggins", "thief")));


//            Optional<Order> nextOrder1 = ordersService.getNextOrder();
//            log.info("nextOrder1: {}", nextOrder1);
//
//            Optional<Order> nextOrder2 = ordersService.getNextOrder();
//            log.info("nextOrder2: {}", nextOrder2);

        };
    }
}
