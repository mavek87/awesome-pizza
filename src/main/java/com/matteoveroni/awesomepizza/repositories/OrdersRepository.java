package com.matteoveroni.awesomepizza.repositories;

import com.matteoveroni.awesomepizza.model.Order;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface OrdersRepository extends JpaRepository<Order, Long> {

//    Optional<Order> findFirstByOrderByDateAscAndOrderState(OrderState orderState);

//    Optional<Order> findOrderStateAndFirstByOrderByDateAsc(OrderState orderState);


    @Override
    <S extends Order> List<S> saveAll(Iterable<S> entities);

    @Query(value = "SELECT o FROM Order o WHERE o.orderState=1 ORDER BY o.date ASC LIMIT 1")
    Optional<Order> findNextOrderToProcess();

}
