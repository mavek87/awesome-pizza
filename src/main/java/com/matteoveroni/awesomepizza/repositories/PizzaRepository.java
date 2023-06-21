package com.matteoveroni.awesomepizza.repositories;

import com.matteoveroni.awesomepizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}