package org.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderLocation")
public class OrderLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String longitude;
    private String latitude;
    private String areaDescription;
    private String areaName;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
