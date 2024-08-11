package com.spring_cloud.eureka.client.order.entity.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponseDto(
        Long orderId,
        List<Long> productIds
) {
}
