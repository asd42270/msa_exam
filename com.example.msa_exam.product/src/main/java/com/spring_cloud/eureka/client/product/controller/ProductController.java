package com.spring_cloud.eureka.client.product.controller;

import com.spring_cloud.eureka.client.product.entity.dto.ProductInsertRequestDto;
import com.spring_cloud.eureka.client.product.entity.dto.ProductResponseDto;
import com.spring_cloud.eureka.client.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String serverPort;
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Server-port", serverPort);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(productService.getProducts());
    }

    @PostMapping()
    public ResponseEntity<Void> addProduct(
            @RequestBody ProductInsertRequestDto requestDto
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Server-port", serverPort);
        productService.addProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .build();
    }
}
