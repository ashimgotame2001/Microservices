package org.example.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderStatusRequest {
    private String orderNumber;
    private String status;
}
