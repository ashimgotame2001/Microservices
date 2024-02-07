package org.example.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private String skuCode;
    private Integer quantity;
}
