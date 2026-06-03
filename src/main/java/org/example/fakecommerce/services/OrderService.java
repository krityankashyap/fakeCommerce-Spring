package org.example.fakecommerce.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.el.lang.FunctionMapperImpl;
import org.aspectj.weaver.ast.Or;
import org.example.fakecommerce.Adapter.OrderAdapter;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.Repositories.OrderRepository;
import org.example.fakecommerce.Repositories.ProductRepository;
import org.example.fakecommerce.dtos.*;
import org.example.fakecommerce.exceptions.ResourceNotFoundException;
import org.example.fakecommerce.schema.Order;
import org.example.fakecommerce.schema.OrderStatus;
import org.example.fakecommerce.schema.Order_Products;
import org.example.fakecommerce.schema.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final OrderAdapter orderAdapter;

    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, OrderAdapter orderAdapter) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderAdapter = orderAdapter;
    }

    public List<GetOrderResponseDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(order -> orderAdapter.mapToGetOrderResponseDto(order))
                .collect(Collectors.toList());
    }

    public GetOrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with corresponding id is not found"));

        return orderAdapter.mapToGetOrderResponseDto(order);
    }

    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        orderRepository.delete(order);
    }

    @Transactional
    public GetOrderResponseDto createOrder(CreateOrderRequestDto createOrderRequestDto) {
        // create an order
        Order order = Order.builder()
                .orderStatus(OrderStatus.PENDING)
                .build();


        // search product by productId
        if (createOrderRequestDto.getOrderItems() != null) {
            for (var itemdto : createOrderRequestDto.getOrderItems()) {
                Product product = productRepository.findById(itemdto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Problem with this id: not found"));

                // create order_product

                Order_Products orderProducts = Order_Products.builder()
                        .order(order)
                        .product(product)
                        .quantity(itemdto.getQuantity() != null ? itemdto.getQuantity() : 1)
                        .build();

                orderProductRepository.save(orderProducts);
            }
        }
        return orderAdapter.mapToGetOrderResponseDto(order);

    }

    @Transactional
    public GetOrderResponseDto updateOrder(Long id, UpdateOrderDto updateOrderRequestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));


        if (updateOrderRequestDto.getStatus() != null) {
            order.setOrderStatus(OrderStatus.valueOf(String.valueOf(updateOrderRequestDto.getStatus())));
            orderRepository.save(order);
        }

        if (updateOrderRequestDto.getOderitems() != null) {
            List<Long> productIds = updateOrderRequestDto.getOderitems().stream().map(item -> item.getProductId()).collect(Collectors.toList());

            List<Product> products = productRepository.findAllById(productIds);

            Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

            for (var pid : productIds) {
                if (!productMap.containsKey(pid)) {
                    throw new ResourceNotFoundException("Product not found with id: " + pid);
                }
            }

            List<Order_Products> toSave = new ArrayList<>();
            List<Order_Products> toDelete = new ArrayList<>();

            Map<Long, Order_Products> existingItems = orderProductRepository.findByOrderWithProduct(order)
                    .stream().collect(Collectors.toMap(op -> op.getProduct().getId(), Function.identity()));

            for (OrderRequestActionsDto itemAction : updateOrderRequestDto.getOderitems()) {
                Product product = productMap.get(itemAction.getProductId());

                Order_Products existing = existingItems.get(product.getId());

                switch (itemAction.getOrderRequestActions()) {
                    case ADD -> {
                        if (existing != null) {
                            int addQty = (itemAction.getQuantity() != null ? itemAction.getQuantity() : 1);
                            existing.setQuantity(existing.getQuantity() + addQty);
                            toSave.add(existing);
                        } else {
                            Order_Products newItem = Order_Products
                                    .builder()
                                    .order(order)
                                    .product(product)
                                    .quantity(itemAction.getQuantity() != null ? itemAction.getQuantity() : 1)
                                    .build();
                            existingItems.put(product.getId(), newItem);
                            toSave.add(newItem);
                        }
                    }
                    case REMOVE -> {
                        if (existing == null) {
                            throw new ResourceNotFoundException("Product not found with id: " + product.getId());
                        }
                        toDelete.add(existing);
                        existingItems.remove(product.getId());
                    }
                    case INCREMENT -> {
                        if (existing == null) {
                            throw new ResourceNotFoundException("Product not found with id: " + product.getId());
                        }
                        existing.setQuantity(existing.getQuantity() + 1);
                        toSave.add(existing);

                    }
                    case DECREMENT -> {
                        if (existing == null) {
                            throw new ResourceNotFoundException("Product not found with id: " + product.getId());
                        }
                        if (existing.getQuantity() <= 1) {
                            toDelete.add(existing);
                            existingItems.remove(product.getId());
                        } else {
                            existing.setQuantity(existing.getQuantity() - 1);
                            toSave.add(existing);
                        }


                    }
                }

            }

            if (!toSave.isEmpty()) {
                orderProductRepository.saveAll(toSave);
            }
            if (!toDelete.isEmpty()) {
                orderProductRepository.deleteAll(toDelete);
            }

        }

        return orderAdapter.mapToGetOrderResponseDto(order);

    }


    public GetOrderSummaryResponseDto getOrderSummary(Long id) {

        Order order = this.orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found with this id:"));

        List<Order_Products> orderProducts= this.orderProductRepository.findByOrderWithProduct(order); // give product details in terms of orderproduct

        List<OrderItemsResponseDto> items= orderAdapter.mapToOrderItemResponseDto(orderProducts);

        int totalItems= orderProducts.stream()
                .mapToInt(Order_Products:: getQuantity).sum();

        BigDecimal totalPrice= orderProducts.stream()
                .map(op-> op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return GetOrderSummaryResponseDto.builder()
                .id(order.getId())
                .status(order.getOrderStatus())
                .items(items)
                .totalItems(totalItems)
                .totalPrice(totalPrice)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();


    }
}





