package org.example.fakecommerce.Adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fakecommerce.Repositories.OrderProductRepository;
import org.example.fakecommerce.dtos.GetOrderResponseDto;
import org.example.fakecommerce.dtos.OrderItemRequestDto;
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

    public OrderAdapter(OrderProductRepository orderProductRepository){
        this.orderProductRepository= orderProductRepository;
    }

    public GetOrderResponseDto mapToGetOrderResponseDto(Order order){

        List<Order_Products> orderProducts= orderProductRepository.findByOrderId(order.getId());

        return GetOrderResponseDto.builder()
                .id(order.getId())
                .status(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderItems(mapToOrderItemResponseDto(orderProducts))
                .build();
    }

    public List<OrderItemRequestDto> mapToOrderItemResponseDto(List<Order_Products> orderProducts){
        return orderProducts.stream()
                .map(op -> OrderItemRequestDto.builder()
                        .productId(op.getProduct().getId())
                        .prodName(op.getProduct().getTitle())
                        .prodImage(op.getProduct().getImage())
                        .prodPrice(op.getProduct().getPrice())
                        .quantity(op.getQuantity())
                        .subTotal(op.getProduct().getPrice().multiply(BigDecimal.valueOf(op.getQuantity())))
                        .build())
                .collect(Collectors.toList());
    }
}
