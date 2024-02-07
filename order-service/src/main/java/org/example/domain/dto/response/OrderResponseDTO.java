package org.example.domain.dto.response;

import lombok.*;
import org.example.domain.OrderLineItems;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO<T> {
    private String orderNumber;
    private String orderStatus;
    private LocalDateTime orderDate;
    private List<OrderLineItems> orderedItems;
}
