package org.example.domain.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.ProductCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductRequestCustomDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> colors;
    private Integer quantity;
    private List<ProductCategory> productCategories;
}
