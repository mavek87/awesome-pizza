package com.matteoveroni.awesomepizza.model;

import com.matteoveroni.awesomepizza.model.converters.OrderStateEnumConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="order_items",
            joinColumns = @JoinColumn( name="order_id"),
            inverseJoinColumns = @JoinColumn( name="order_item_id")
    )
    private List<OrderItem> orderItems;
    @Column(name="order_state")
    @Convert(converter = OrderStateEnumConverter.class)
    private OrderState orderState;
    private Date date = new Date();
    private String notes = "";
}