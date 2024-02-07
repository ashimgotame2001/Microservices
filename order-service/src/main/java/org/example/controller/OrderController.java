package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.dto.request.OrderCancelRequest;
import org.example.domain.dto.request.OrderRequest;
import org.example.domain.dto.request.OrderStatusRequest;
import org.example.response.BaseResponse;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public BaseResponse placeOrder(@RequestBody OrderRequest orderRequest) throws IllegalAccessException {
      return orderService.placeOrder(orderRequest);
    }

    @PostMapping("/cancel")
    public BaseResponse cancelOrder(@RequestBody OrderCancelRequest cancelRequest) throws IllegalAccessException {
        return orderService.cancelOrder(cancelRequest);
    }

    @PostMapping("/change-status")
    public BaseResponse cancelOrder(@RequestBody OrderStatusRequest orderStatusRequest) throws IllegalAccessException {
        return orderService.changeOrderStatus(orderStatusRequest);
    }

}
