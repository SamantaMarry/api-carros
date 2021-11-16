package com.wswork.teste.samantamarry.apicarros.domain;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", factories=" + factories +
                ", modal='" + modal + '\'' +
                ", year=" + year +
                ", fuel='" + fuel + '\'' +
                ", doors=" + doors +
                ", cost=" + cost +
                ", color='" + color + '\'' +
                '}';
    }
}
