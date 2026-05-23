package org.example.fakecommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql= "UPDATE orders SET deleted_at= CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "orders")
public class Order extends BaseClass{

    private OrderStatus orderStatus;

//    @ManyToMany
//    @JoinTable(name = "Order_Product" ,
//    joinColumns = @JoinColumn(name = "order_id") ,
//    inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private List<Product> product;
//
    @OneToMany(mappedBy = "order")
    private List<Review> reviews;
}
