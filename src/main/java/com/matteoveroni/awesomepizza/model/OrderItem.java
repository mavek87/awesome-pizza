package com.matteoveroni.awesomepizza.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "pizza_id", referencedColumnName = "id")
    private Pizza pizza;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private Order order;
    private Integer amountOfPizza;
}
