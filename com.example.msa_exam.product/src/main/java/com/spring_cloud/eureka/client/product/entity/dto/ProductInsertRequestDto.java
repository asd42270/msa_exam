package com.spring_cloud.eureka.client.product.entity.dto;

public record ProductInsertRequestDto(
        String name,
        Integer supplyPrice
) {
}
