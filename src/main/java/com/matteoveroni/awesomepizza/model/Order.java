package com.matteoveroni.awesomepizza.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matteoveroni.awesomepizza.model.converters.OrderStateEnumConverter;
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
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id")
    )
    @JsonIgnore
    private List<OrderItem> orderItems;
    @Column(name = "order_state")
    @Convert(converter = OrderStateEnumConverter.class)
    private OrderState orderState;
    private Date date;
    @Builder.Default
    private String notes = "";
}