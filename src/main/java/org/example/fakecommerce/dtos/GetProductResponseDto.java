package org.example.fakecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductResponseDto {

    private Long id;

    private String title;

    private String description;

    private String image;

    private String ratings;

    private BigDecimal price;
}
