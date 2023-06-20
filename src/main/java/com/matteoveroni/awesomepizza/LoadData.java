package com.matteoveroni.awesomepizza;

import com.matteoveroni.awesomepizza.factories.PizzaFactory;
import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderItem;
import com.matteoveroni.awesomepizza.model.OrderState;
import com.matteoveroni.awesomepizza.model.PizzaName;
import com.matteoveroni.awesomepizza.repositories.OrderItemsRepository;
import com.matteoveroni.awesomepizza.repositories.OrdersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.matteoveroni.awesomepizza.services.OrdersService;
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
    CommandLineRunner initDatabase(OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository, OrdersService ordersService) {

        return args -> {
//            Session session = getCurrentSessionFromJPA().openSession();
//            session.beginTransaction();

            OrderItem item1Order1 = OrderItem.builder()
                    .pizza(PizzaFactory.createPizza(PizzaName.MARGHERITA))
                    .amountOfPizza(3)
                    .build();

            orderItemsRepository.save(item1Order1);

            Order order1 = Order.builder()
                    .date(new Date())
                    .orderState(OrderState.TO_PREPARE)
                    .orderItems(List.of(item1Order1))
                    .build();
//
            ordersRepository.save(order1);


            List<Order> all = ordersRepository.findAll();


//            session.getTransaction().commit();
//            session.close();


            log.info("all => " + all);

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