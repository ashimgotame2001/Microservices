package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.domain.OrderDetail;
import org.example.domain.OrderLineItems;
import org.example.domain.dto.request.OrderCancelRequest;
import org.example.domain.dto.request.OrderCancellationRequest;
import org.example.domain.dto.request.OrderRequest;
import org.example.domain.dto.request.OrderStatusRequest;
import org.example.domain.dto.response.InventoryResponse;
import org.example.domain.enumuration.OrderStatus;
import org.example.repository.OrderRepository;
import org.example.response.BaseResponse;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;

    @Value("${inventory.domain}")
    private String inventoryDomain;

    @Override
    public BaseResponse placeOrder(OrderRequest orderRequest) throws IllegalAccessException {
        Order order = new Order();
        order.setOrderNumber(String.valueOf(UUID.randomUUID()));
        order.setOrderBy(orderRequest.getOrderBy());
        order.setOrderDate(LocalDateTime.now());
        order.setOrderLineItemsList(orderRequest.getOrderLineItemsList());
        order.setLocation(orderRequest.getLocation());
        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        String inventoryUri = UriComponentsBuilder.fromUriString(inventoryDomain)
                .path("/api/inventory")
                .build()
                .toString();
        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri(inventoryUri, uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponses != null;
        boolean allProductInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if (allProductInStock) {
            orderRepository.save(order);
            String inventoryUriToHandleOrder = UriComponentsBuilder.fromUriString(inventoryDomain)
                    .path("/api/inventory/order")
                    .build()
                    .toString();
            webClient.build().post()
                    .uri(inventoryUriToHandleOrder)
                    .body(Mono.just(orderRequest.getOrderLineItemsList()), List.class)
                    .exchangeToMono(response -> response.bodyToMono(Void.class))
                    .block();
            return BaseResponse.builder().message("Order Placed Successfully").statusCode(HttpStatus.OK.value()).build();
        } else {
            return BaseResponse.builder().message("Product, Out of stock").statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }

    }

    @Transactional
    @Override
    public BaseResponse cancelOrder(OrderCancelRequest cancelRequest) {

        Optional<Order> order = orderRepository.findByOrderNumber(cancelRequest.getOrderNumber());
        if (order.isEmpty()) {
            return BaseResponse.builder().message("Invalid order detail !!!").statusCode(HttpStatus.NOT_FOUND.value()).build();
        }
        order.get().setOrderStatus(OrderStatus.CANCELLATION_REQUESTED.name());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetail(cancelRequest.getOrderCancellationReason());
        orderDetail.setOrder(order.get());
        order.get().setOrderDetail(orderDetail);
        orderRepository.save(order.get());
        return BaseResponse.builder().message("Order cancellation requested").statusCode(HttpStatus.OK.value()).build();
    }

    @Override
    public BaseResponse changeOrderStatus(OrderStatusRequest orderStatusRequest) {
        Optional<Order> order = orderRepository.findByOrderNumber(orderStatusRequest.getOrderNumber());
        if (order.isEmpty()) {
            return BaseResponse.builder().message("Invalid order detail !!!").statusCode(HttpStatus.NOT_FOUND.value()).build();
        }
        if (Objects.equals(orderStatusRequest.getStatus(), OrderStatus.CANCELLATION_APPROVED.name())){
            OrderCancellationRequest orderCancellationRequest = OrderCancellationRequest.builder().build();
            String inventoryUriToHandleOrder = UriComponentsBuilder.fromUriString(inventoryDomain)
                    .path("/api/inventory/order-cancel")
                    .build()
                    .toString();
            webClient.build().post()
                    .uri(inventoryUriToHandleOrder)
                    .body(Mono.just(orderCancellationRequest), OrderCancellationRequest.class)
                    .exchangeToMono(response -> response.bodyToMono(Void.class))
                    .block();
        }

        order.get().setOrderStatus(orderStatusRequest.getStatus());
        orderRepository.save(order.get());
        return BaseResponse.builder().message("Order status changed").statusCode(HttpStatus.OK.value()).build();
    }

    @Transactional
    @Override
    public BaseResponse getAllOrders() {
        return null;
    }

}
