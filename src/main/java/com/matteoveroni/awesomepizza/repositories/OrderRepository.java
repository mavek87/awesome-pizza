package com.matteoveroni.awesomepizza.repositories;

import com.matteoveroni.awesomepizza.model.Order;
import com.matteoveroni.awesomepizza.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
