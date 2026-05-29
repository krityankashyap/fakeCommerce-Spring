package org.example.fakecommerce.Repositories;

import org.example.fakecommerce.dtos.GetProductWithDetailsResponseDto;
import org.example.fakecommerce.schema.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_Name(String categoryName);

    @Query(nativeQuery = true, value = "SELECT DISTINCT category FROM products")
    List<String> findAllCategories();

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id= :id")
    List<Product> findProductWithDetailsById(Long id);
}