package com.wswork.teste.samantamarry.apicarros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factories_id")
    private Factories factories;

    private String modal;

    private Integer year;

    private String fuel;

    private Integer doors;

    private BigDecimal cost;

    private String color;

}
