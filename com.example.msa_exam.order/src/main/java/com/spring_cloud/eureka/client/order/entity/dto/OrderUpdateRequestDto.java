package com.spring_cloud.eureka.client.order.entity.dto;

import lombok.Builder;

@Builder
public record OrderUpdateRequestDto(
        Long productId
) {
}
