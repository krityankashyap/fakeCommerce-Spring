package org.example.fakecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fakecommerce.schema.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderResponseDto {

    private Long id;

    private OrderStatus status;

    private List<OrderItemsResponseDto> orderItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
