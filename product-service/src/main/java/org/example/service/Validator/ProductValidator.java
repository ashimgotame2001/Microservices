package org.example.service.Validator;

import lombok.RequiredArgsConstructor;
import org.example.domain.Product;
import org.example.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductValidator {

    public BaseResponse createValidator(Product product){
        return null;
    }
}
