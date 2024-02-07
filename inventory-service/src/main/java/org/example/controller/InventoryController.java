package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.InventoryResponse;
import org.example.domain.dto.request.OrderCancellationRequest;
import org.example.domain.dto.request.OrderRequestDTO;
import org.example.domain.dto.request.ProductRequestDTO;
import org.example.response.BaseResponse;
import org.example.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping("/order")
    void handleOrder(@RequestBody List<OrderRequestDTO> orderRequestDTOs){
        inventoryService.handleOrder(orderRequestDTOs);
    }

    @PostMapping("/product")
    BaseResponse addProductToInventory(@RequestBody ProductRequestDTO productRequestDTO){
        return inventoryService.addToInventory(productRequestDTO);
    }

    @PostMapping("/order-cancel")
    BaseResponse cancelOrder(@RequestBody OrderCancellationRequest orderCancellationRequest){
        return inventoryService.handleCancellation(orderCancellationRequest);
    }


}
