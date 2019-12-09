package org.fasttrackit.shoppinglist.persistance;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    @Query(value = "SELECT * FROM shopping_list WHERE name LIKE '%?0'", nativeQuery = true)
    Page<ShoppingList> findByPartialName(String partialName, Pageable pageable);
}
