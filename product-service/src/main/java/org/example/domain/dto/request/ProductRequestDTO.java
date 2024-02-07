package org.example.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {
    private String skuCode;
    private Integer quantity;
    private BigDecimal pricePerPiece;
}
