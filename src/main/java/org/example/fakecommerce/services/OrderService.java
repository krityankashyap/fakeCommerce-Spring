package org.example.fakecommerce.services;

import lombok.RequiredArgsConstructor;
import org.example.fakecommerce.Adapter.OrderAdapter;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.Repositories.OrderRepository;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.example.fakecommerce.dtos.GetOrderResponseDto;
import org.example.fakecommerce.exceptions.ResourceNotFoundException;
import org.example.fakecommerce.schema.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderAdapter orderAdapter;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, OrderAdapter orderAdapter){
        this.orderRepository= orderRepository;
        this.orderProductRepository= orderProductRepository;
        this.productRepository= productRepository;
        this.orderAdapter = orderAdapter;
    }

    public List<GetOrderResponseDto> getAllOrders(){
        List<Order> orders= orderRepository.findAll();

        return orders.stream()
                .map(order -> orderAdapter.mapToGetOrderResponseDto(order))
                .collect(Collectors.toList());
    }

    public GetOrderResponseDto getOrderById(Long id){
        Order order= orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with corresponding id is not found"));

        return orderAdapter.mapToGetOrderResponseDto(order);
    }

    public void  deleteOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        orderRepository.delete(order);
    }


}
