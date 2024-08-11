package com.spring_cloud.eureka.client.order.controller;

import com.spring_cloud.eureka.client.order.entity.dto.OrderInsertRequestDto;
import com.spring_cloud.eureka.client.order.entity.dto.OrderResponseDto;
import com.spring_cloud.eureka.client.order.entity.dto.OrderUpdateRequestDto;
import com.spring_cloud.eureka.client.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping()
    public ResponseEntity<Void> addOrder(
            @RequestBody OrderInsertRequestDto requestDto
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Server-port", serverPort);

        orderService.addOrder(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody OrderUpdateRequestDto requestDto
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Server-port", serverPort);

        orderService.updateOrder(orderId, requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(
            @PathVariable("orderId") Long orderId
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Server-port", serverPort);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(orderService.getOrder(orderId));
    }
}
