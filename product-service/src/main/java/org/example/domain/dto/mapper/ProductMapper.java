package org.example.domain.dto.mapper;

import lombok.experimental.UtilityClass;
import org.example.domain.Product;
import org.example.domain.dto.request.ProductRequestCustomDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public static ProductRequestCustomDTO toResponse (Product product){
        ProductRequestCustomDTO productRequestCustomDTO = ProductRequestCustomDTO
                .builder()
                .productCategories(product.getProductCategories())
                .name(product.getName())
                .price(product.getPrice())
                .colors(product.getColors())
                .description(product.getDescription())
                .build();
        return productRequestCustomDTO;
    };

    public static Product toEntity (ProductRequestCustomDTO productRequestCustomDTO){
        Product product = Product
                .builder()
                .productCategories(productRequestCustomDTO.getProductCategories())
                .name(productRequestCustomDTO.getName())
                .price(productRequestCustomDTO.getPrice())
                .colors(productRequestCustomDTO.getColors())
                .description(productRequestCustomDTO.getDescription())
                .build();
        return product;
    };
}
