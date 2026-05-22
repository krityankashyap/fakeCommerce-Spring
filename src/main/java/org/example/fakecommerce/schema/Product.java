package org.example.fakecommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SQLDelete(sql= "UPDATE products SET deleted_at= CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
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
