package com.spring_cloud.eureka.client.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "orders")
    @Setter
    private List<OrdersProduct> productIds = new ArrayList<>();

    public void addOrdersProduct(OrdersProduct ordersProduct) {
        this.productIds.add(ordersProduct);
    }
}
