package com.spring_cloud.eureka.client.order.service;

import com.spring_cloud.eureka.client.order.entity.Orders;
import com.spring_cloud.eureka.client.order.entity.OrdersProduct;
import com.spring_cloud.eureka.client.order.entity.dto.OrderInsertRequestDto;
import com.spring_cloud.eureka.client.order.entity.dto.OrderResponseDto;
import com.spring_cloud.eureka.client.order.entity.dto.OrderUpdateRequestDto;
import com.spring_cloud.eureka.client.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(OrderInsertRequestDto requestDto) {
        // 주문을 저장
        Orders orders = Orders.builder()
                .name(requestDto.name())
                .build();

        // 주문 매핑 상품 생성
        List<OrdersProduct> orderProducts = requestDto.productIds().stream()
                .map(productId -> OrdersProduct.builder()
                        .orders(orders)  // Order 객체를 설정
                        .productId(productId)
                        .build())
                .toList();

        orders.setProductIds(orderProducts);

        // 주문과 주문 매핑 상품을 함께 저장
        orderRepository.save(orders);

    }

    public void updateOrder(Long orderId, OrderUpdateRequestDto requestDto) {

        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot Find Order")
        );

        OrdersProduct ordersProduct = OrdersProduct.builder()
                .orders(orders)
                .productId(requestDto.productId())
                .build();

        orders.addOrdersProduct(ordersProduct);
    }

    public OrderResponseDto getOrder(Long orderId) {

        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot Find Order")
        );

        return OrderResponseDto.builder()
                .orderId(orders.getId())
                .productIds(orders.getProductIds().stream()
                        .map(OrdersProduct::getProductId)
                        .collect(Collectors.toList()))
                .build();
    }
}
