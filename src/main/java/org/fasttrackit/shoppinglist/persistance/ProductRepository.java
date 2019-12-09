package org.fasttrackit.shoppinglist.persistance;

import org.fasttrackit.shoppinglist.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE name LIKE '%?0'", nativeQuery = true)
    Page<Product> findByPartialName(String partialName, Pageable pageable);
}
