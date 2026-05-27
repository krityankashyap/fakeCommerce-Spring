package org.example.fakecommerce.dtos;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateOrderRequestDto {

    private List<OrderItemRequestDto> orderItems;
}
