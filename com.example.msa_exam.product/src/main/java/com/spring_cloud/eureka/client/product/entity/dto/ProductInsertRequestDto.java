package com.spring_cloud.eureka.client.product.entity.dto;

public record ProductInsertRequestDto(
        Long productId,
        String name,
        Integer supplyPrice
) {
}
