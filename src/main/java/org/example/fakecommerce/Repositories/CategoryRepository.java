package org.example.fakecommerce.Repositories;

import org.example.fakecommerce.schema.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
