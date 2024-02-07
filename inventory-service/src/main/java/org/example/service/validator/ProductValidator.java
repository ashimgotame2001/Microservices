package org.example.service.validator;


import lombok.RequiredArgsConstructor;
import org.example.domain.dto.request.ProductRequestDTO;
import org.example.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductValidator {

    public BaseResponse validate(ProductRequestDTO productRequestDTO){
        BaseResponse baseResponse = new BaseResponse();
        if(productRequestDTO.getQuantity() == null || productRequestDTO.getQuantity() <= 0){
            return BaseResponse.builder().message("Invalid product quantity !!!").build();
        }
        if(productRequestDTO.getSkuCode() == null){
            return BaseResponse.builder().message("SkuCode mustn't be null !!!").build();
        }
        if(productRequestDTO.getPricePerPiece() == null){
            return BaseResponse.builder().message("Invalid price per piece !!!").build();
        }
        return baseResponse;
    }
}
