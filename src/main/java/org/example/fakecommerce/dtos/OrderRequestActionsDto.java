package org.example.fakecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestActionsDto {

    private String productId;

    private Integer quantity;

    private OrderRequestActions orderRequestActions;
}
