package com.matteoveroni.awesomepizza.model;

import com.matteoveroni.awesomepizza.repositories.OrderItemsRepository;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pizza")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name = "";
    private String description = "";
    private BigDecimal price = new BigDecimal("0.0");
    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

}
