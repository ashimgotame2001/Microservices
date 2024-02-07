package org.example.service;

import org.example.domain.dto.request.OrderCancelRequest;
import org.example.domain.dto.request.OrderRequest;
import org.example.domain.dto.request.OrderStatusRequest;
import org.example.response.BaseResponse;

public interface OrderService {

    BaseResponse placeOrder(OrderRequest orderRequest) throws IllegalAccessException;

    BaseResponse cancelOrder(OrderCancelRequest cancelRequest);

    BaseResponse changeOrderStatus(OrderStatusRequest orderStatusRequest);

    BaseResponse getAllOrders();

}
