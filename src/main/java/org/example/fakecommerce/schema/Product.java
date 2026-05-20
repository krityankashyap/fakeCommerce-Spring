package org.example.fakecommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product extends BaseClass{

    private String title;

    private String description;

    private BigDecimal price;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" , nullable = false)
    private Category category;

    private String ratings;
}
