package com.spring_cloud.eureka.client.product.entity.dto;

import lombok.Builder;

@Builder
public record ProductResponseDto(
        Long productId,
        String name,
        Integer supplyPrice

) {
}
