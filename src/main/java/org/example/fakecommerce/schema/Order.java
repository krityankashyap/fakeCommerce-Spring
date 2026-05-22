package org.example.fakecommerce.schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order extends BaseClass{

    private OrderStatus orderStatus;

    @ManyToMany
    @JoinTable(name = "Order-Product" ,
    joinColumns = @JoinColumn(name = "order_id") ,
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product;


}
