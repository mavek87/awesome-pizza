package com.matteoveroni.awesomepizza.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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
