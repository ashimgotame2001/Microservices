package org.example.service;

import org.example.domain.Product;
import org.example.domain.dto.request.ProductRequestCustomDTO;
import org.example.response.BaseResponse;

import java.util.List;

public interface ProductService {

    BaseResponse addProduct(ProductRequestCustomDTO productRequestCustomDTO);

    List<Product> getAllProducts();
}
