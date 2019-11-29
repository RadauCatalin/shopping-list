package org.fasttrackit.shoppinglist.persistance;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
