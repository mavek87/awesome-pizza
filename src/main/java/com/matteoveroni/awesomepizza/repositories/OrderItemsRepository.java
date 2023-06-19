package com.matteoveroni.awesomepizza.repositories;

import com.matteoveroni.awesomepizza.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
