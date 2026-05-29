package org.example.fakecommerce.Repositories;

import org.example.fakecommerce.schema.Order;
import org.example.fakecommerce.schema.Order_Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<Order_Products, Long> {

    List<Order_Products> findByOrderId(Long orderId);

   @Query("SELECT op FROM Order_Products op JOIN FETCH op.product WHERE op.order= :order")
    List<Order_Products> findByOrderWithProduct(Order order);
}
