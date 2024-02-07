package org.example.domain;

import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
    private BigDecimal pricePerPiece;
}
