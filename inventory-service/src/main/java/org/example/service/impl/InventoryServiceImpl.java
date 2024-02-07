package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.domain.Inventory;
import org.example.domain.dto.InventoryResponse;
import org.example.domain.dto.request.OrderCancellationRequest;
import org.example.domain.dto.request.OrderRequestDTO;
import org.example.domain.dto.request.ProductRequestDTO;
import org.example.repository.InventoryRepository;
import org.example.response.BaseResponse;
import org.example.service.InventoryService;
import org.example.service.validator.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductValidator productValidator;

    @Override
    public BaseResponse addToInventory(ProductRequestDTO productRequestDTO) {
         BaseResponse baseResponse = productValidator.validate(productRequestDTO);
         if(baseResponse.getData() != null){
             return baseResponse;
         }
         Optional<Inventory> inventory = inventoryRepository.findBySkuCode(productRequestDTO.getSkuCode());
              if (inventory.isEmpty()){
                  Inventory inventory1 = Inventory
                          .builder()
                          .quantity(productRequestDTO.getQuantity())
                          .pricePerPiece(productRequestDTO.getPricePerPiece())
                          .skuCode(productRequestDTO.getSkuCode())
                          .build();
                  inventoryRepository.save(inventory1);
              }else {
                  inventory.get().setQuantity(inventory.get().getQuantity() + productRequestDTO.getQuantity());
                  inventoryRepository.save(inventory.get());
              }
        return BaseResponse.builder().statusCode(HttpStatus.CREATED.value()).message("Product successfully added to inventory").build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> code) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(code);
        Map<String, Boolean> skuCodeToInStockMap = inventoryList.stream()
                .collect(Collectors.toMap(Inventory::getSkuCode, inventory -> inventory.getQuantity() > 0));

        return code.stream()
                .map(skuCode ->
                        InventoryResponse.builder()
                                .skuCode(skuCode)
                                .isInStock(skuCodeToInStockMap.getOrDefault(skuCode, false))
                                .build()
                )
                .toList();
    }

    @Override
    public void handleOrder(List<OrderRequestDTO> orderRequestDTOs) {
      orderRequestDTOs.stream()
              .map(orderRequestDTO -> {
                  Inventory inventory = inventoryRepository.findBySkuCode(orderRequestDTO.getSkuCode()).get();
                  inventory.setQuantity(inventory.getQuantity() - orderRequestDTO.getQuantity());
                 return inventoryRepository.save(inventory);
              }).toList();
    }

    @Override
    public BaseResponse handleCancellation(OrderCancellationRequest orderCancellationRequest) {
        Optional<Inventory>  inventory = inventoryRepository.findBySkuCode(orderCancellationRequest.getSkuCode());
        if(inventory.isEmpty()){
            return BaseResponse.builder().message("Inventory not found").statusCode(HttpStatus.OK.value()).build();
        }
        inventory.get().setQuantity(inventory.get().getQuantity() + orderCancellationRequest.getQuantity());
        inventoryRepository.save(inventory.get());
        return BaseResponse.builder().message("Order cancelled").statusCode(HttpStatus.OK.value()).build();
    }

}
