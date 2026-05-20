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
public class CreateProductDto {

    private String title;

    private Long category_id;

    private String description;

    private String image;

    private BigDecimal price;

    private String ratings;
}
