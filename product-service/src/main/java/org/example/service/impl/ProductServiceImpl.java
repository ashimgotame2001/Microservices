package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.Product;
import org.example.domain.dto.mapper.ProductMapper;
import org.example.domain.dto.request.ProductRequestCustomDTO;
import org.example.domain.dto.request.ProductRequestDTO;
import org.example.repository.ProductRepository;
import org.example.response.BaseResponse;
import org.example.service.ProductService;
import org.example.service.Validator.ProductValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductValidator productValidator;
    private final WebClient.Builder webClient;




    @Value("${inventory.domain}")
    private String inventoryDomain;
    @Override
    public BaseResponse addProduct(ProductRequestCustomDTO productRequestCustomDTO) {
//        BaseResponse baseResponse = productValidator.createValidator(ProductMapper.toEntity(productRequestCustomDTO));
//        if (baseResponse != null) {
//            return baseResponse;
//        }

        Product product = ProductMapper.toEntity(productRequestCustomDTO);
        product.setProductCode(UUID.randomUUID());
        product.setSkuCode(product.getProductCode().toString());
        product.setSlug(product.getName());
        product.setCreatedDate(LocalDateTime.now());
        Product recentProduct = productRepository.save(product);

        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setQuantity(productRequestCustomDTO.getQuantity());
        productRequestDTO.setSkuCode(recentProduct.getSkuCode());
        productRequestDTO.setPricePerPiece(recentProduct.getPrice());

        String inventoryUriToHandleProduct = UriComponentsBuilder.fromUriString(inventoryDomain)
                .path("/api/inventory/product")
                .build()
                .toString();

        webClient.build().post()
                .uri(inventoryUriToHandleProduct)
                .body(Mono.just(productRequestDTO), ProductRequestDTO.class)
                .exchangeToMono(response -> response.bodyToMono(Void.class))
                .block();

        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product successfully added")
                .build();
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
