package org.example.domain.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCancelRequest {
    private String orderNumber;
    private String orderCancellationReason;
}
