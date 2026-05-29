package org.example.fakecommerce.Adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.dtos.GetOrderResponseDto;
import org.example.fakecommerce.dtos.OrderItemRequestDto;
import org.example.fakecommerce.dtos.OrderItemsResponseDto;
import org.example.fakecommerce.schema.Order;
import org.example.fakecommerce.schema.Order_Products;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Component
public class OrderAdapter {

    private final OrderProductRepository orderProductRepository;

    public List<GetOrderResponseDto> mapToGetOrderResponseDtoList(List<Order> orders) {
        return orders.stream()
                .map(this::mapToGetOrderResponseDto)
                .collect(Collectors.toList());
    }

    public OrderAdapter(OrderProductRepository orderProductRepository){
        this.orderProductRepository= orderProductRepository;
    }

    public GetOrderResponseDto mapToGetOrderResponseDto(Order order){

        List<Order_Products> orderProducts= orderProductRepository.findByOrderId(order.getId());

        List<OrderItemsResponseDto> items = mapToOrderItemResponseDto(orderProducts);

        return GetOrderResponseDto.builder()
                .id(order.getId())
                .status(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderItems(items)
                .build();
    }

    public List<OrderItemsResponseDto> mapToOrderItemResponseDto(List<Order_Products> orderProducts){
        return orderProducts.stream()
                .map(op -> OrderItemsResponseDto.builder()
                        .productId(op.getProduct().getId())
                        .productName(op.getProduct().getTitle())
                        .productImage(op.getProduct().getImage())
                        .productPrice(op.getProduct().getPrice())
                        .quantity(op.getQuantity())
                        .subTotal(op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                        .build())
                .collect(Collectors.toList());
    }
}
