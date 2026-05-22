package org.example.fakecommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql= "UPDATE order_product SET deleted_at= CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "order_product")
public class Order_Products extends BaseClass {

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;


}
