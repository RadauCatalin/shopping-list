package org.fasttrackit.shoppinglist.service;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ShoppingListRepository;
import org.fasttrackit.shoppinglist.transfer.GetShoppingListsRequest;
import org.fasttrackit.shoppinglist.transfer.SaveShoppingListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public ShoppingList getShoppingList(long id) {
        LOGGER.info("Retrieving list {}", id);
        return shoppingListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("List " + id + " does not exist. "));
    }

    public Page<ShoppingList> getShoppingLists(GetShoppingListsRequest request, Pageable pageable) {
        LOGGER.info("Retrieveng lists {}", request);
        if (Objects.nonNull(request) && Objects.nonNull(request.getPartialName())) {
            return shoppingListRepository.findByPartialName(request.getPartialName(), pageable);
        } else {
            return shoppingListRepository.findAll(pageable);
        }
    }

    public ShoppingList updateShoppingList(long id, SaveShoppingListRequest request) {
        LOGGER.info("Updating list {}: {}", id, request);
        ShoppingList shoppingList = getShoppingList(id);
        BeanUtils.copyProperties(request, shoppingList);
        return shoppingListRepository.save(shoppingList);
    }

    public void deleteShoppingList(long id) {
        LOGGER.info("Deleting list {}", id);
        shoppingListRepository.deleteById(id);
        LOGGER.info("Deleted list {}", id);
    }
}