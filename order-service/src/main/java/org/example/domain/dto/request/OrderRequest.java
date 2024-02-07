package org.example.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.OrderLineItems;
import org.example.domain.OrderLocation;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderLineItems> orderLineItemsList;
    private OrderLocation location;
    private UUID orderBy;
}