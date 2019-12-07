package org.fasttrackit.shoppinglist.service;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.persistance.ShoppingListRepository;
import org.fasttrackit.shoppinglist.transfer.SaveShoppingListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListService.class);

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createShoppingList(SaveShoppingListRequest request) {
        LOGGER.info("Creating new shopping list {}", request);
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(request.getName());
        shoppingList.setDescription(request.getDescription());
        shoppingList.setBudget(request.getBudget());
        shoppingList.setRemainingBudget(request.getBudget());

        return shoppingListRepository.save(shoppingList);
    }
}
