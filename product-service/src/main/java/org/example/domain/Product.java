package org.example.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String slug;
    private List<String> colors;
    private List<String> sizes;
    private String skuCode;
    private UUID productCode;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<ProductCategory> productCategories;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<FileStorage> productFiles;



}
