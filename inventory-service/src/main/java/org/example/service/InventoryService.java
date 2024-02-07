package org.example.service;

import org.example.domain.dto.InventoryResponse;
import org.example.domain.dto.request.OrderCancellationRequest;
import org.example.domain.dto.request.OrderRequestDTO;
import org.example.domain.dto.request.ProductRequestDTO;
import org.example.response.BaseResponse;

import java.util.List;

public interface InventoryService {


  BaseResponse addToInventory(ProductRequestDTO productRequestDTO);

  List<InventoryResponse> isInStock(List<String> code);

  void handleOrder(List<OrderRequestDTO> orderRequestDTOs);

  BaseResponse handleCancellation (OrderCancellationRequest orderCancellationRequest);








}
