package com.spring_cloud.eureka.client.order.controller;

import com.spring_cloud.eureka.client.order.entity.dto.OrderInsertRequestDto;
import com.spring_cloud.eureka.client.order.entity.dto.OrderUpdateRequestDto;
import com.spring_cloud.eureka.client.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<Void> addOrder(
            @RequestBody OrderInsertRequestDto requestDto
    ) {
        orderService.addOrder(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody OrderUpdateRequestDto requestDto
    ) {
        orderService.updateOrder(orderId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
