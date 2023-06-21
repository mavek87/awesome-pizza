package com.matteoveroni.awesomepizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Builder.Default private String name = "";
    @Builder.Default private String description = "";
    @Builder.Default private BigDecimal price = new BigDecimal("0.0");
    @OneToMany(mappedBy = "pizza", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
}
