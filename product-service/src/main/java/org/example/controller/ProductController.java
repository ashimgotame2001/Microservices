package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Product;
import org.example.domain.dto.request.ProductRequestCustomDTO;
import org.example.response.BaseResponse;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse createProduct(@RequestBody ProductRequestCustomDTO productRequest) {
      return   productService.addProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
