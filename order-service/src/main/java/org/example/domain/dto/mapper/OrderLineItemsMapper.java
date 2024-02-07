package org.example.domain.dto.mapper;

import org.example.domain.OrderLineItems;
import org.example.domain.dto.request.OrderRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLineItemsMapper {

    public static OrderRequestDTO toResponse (OrderLineItems orderLineItems){
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setSkuCode(orderLineItems.getSkuCode());
        orderRequestDTO.setQuantity(orderLineItems.getQuantity());
        return orderRequestDTO;
    }

    public static List<OrderRequestDTO> toResponses(List<OrderLineItems> orderLineItemsList) {
        return orderLineItemsList.stream()
                .map(OrderLineItemsMapper::toResponse)
                .collect(Collectors.toList());
    }
}
