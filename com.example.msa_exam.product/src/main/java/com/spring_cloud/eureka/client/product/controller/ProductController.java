package com.spring_cloud.eureka.client.product.controller;

import com.spring_cloud.eureka.client.product.entity.Product;
import com.spring_cloud.eureka.client.product.entity.dto.ProductInsertRequestDto;
import com.spring_cloud.eureka.client.product.entity.dto.ProductResponseDto;
import com.spring_cloud.eureka.client.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping()
    public ResponseEntity<Void> addProduct(
            @RequestBody ProductInsertRequestDto requestDto
    ) {
        productService.addProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
