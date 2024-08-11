package com.spring_cloud.eureka.client.order.entity.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderInsertRequestDto(
        String name,
        List<Long> productIds

) {
}
