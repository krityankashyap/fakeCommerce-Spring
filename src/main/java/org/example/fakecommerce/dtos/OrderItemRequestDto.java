package org.example.fakecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequestDto {

    private Long productId;

    private Integer quantity;

    private BigDecimal prodPrice;

    private BigDecimal subTotal;

    private String prodImage;

    private String prodName;

}
