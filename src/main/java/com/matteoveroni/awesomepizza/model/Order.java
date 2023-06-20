package com.matteoveroni.awesomepizza.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @Enumerated(EnumType.STRING) private OrderState orderState;
    private Date date = new Date();
    private String notes = "";
}