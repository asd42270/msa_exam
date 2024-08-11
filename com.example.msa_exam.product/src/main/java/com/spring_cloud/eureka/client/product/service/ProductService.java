package com.spring_cloud.eureka.client.product.service;

import com.spring_cloud.eureka.client.product.entity.Product;
import com.spring_cloud.eureka.client.product.entity.dto.ProductInsertRequestDto;
import com.spring_cloud.eureka.client.product.entity.dto.ProductResponseDto;
import com.spring_cloud.eureka.client.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .name(product.getName())
                        .supplyPrice(product.getSupplyPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public void addProduct(ProductInsertRequestDto requestDto) {

        Product product = Product.builder()
                .productId(requestDto.productId())
                .name(requestDto.name())
                .supplyPrice(requestDto.supplyPrice())
                .build();

        productRepository.save(product);
    }
}
