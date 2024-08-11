package com.spring_cloud.eureka.client.order.controller;

//import com.spring_cloud.eureka.client.product.entity.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:19091/products")
public interface ProductClient {
//    @GetMapping
//    List<ProductResponseDto> getProducts();

}
