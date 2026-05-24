package org.example.fakecommerce.Repositories;

import org.example.fakecommerce.schema.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
