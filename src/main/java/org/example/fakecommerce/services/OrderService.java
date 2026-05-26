package org.example.fakecommerce.services;

import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.Repositories.OrderRepository;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository){
        this.orderRepository= orderRepository;
        this.orderProductRepository= orderProductRepository;
        this.productRepository= productRepository;
    }


}
