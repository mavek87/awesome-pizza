package com.matteoveroni.awesomepizza.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Transactional
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    private Pizza pizza;
//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
//    private Order order;
    private Integer amountOfPizza = 0;
}
