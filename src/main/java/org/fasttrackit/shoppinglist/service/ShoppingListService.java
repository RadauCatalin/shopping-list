package org.fasttrackit.shoppinglist.service;

import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ShoppingListRepository;
import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.GetShoppingListsRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.SaveShoppingListRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.ShoppingListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

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

    public ShoppingListResponse getShoppingList(long id) {
        LOGGER.info("Retrieving list {}", id);
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("List " + id + " does not exist. "));
        ShoppingListResponse response = new ShoppingListResponse();
        response.setId(shoppingList.getId());

        Set<ProductResponse> productInShoppingList = new HashSet<>();

        Iterator<Product> shoppingListIterator = shoppingList.getProducts().iterator();

        while (shoppingListIterator.hasNext()) {
            Product product = shoppingListIterator.next();

            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setBought(product.isBought());

            productInShoppingList.add(productResponse);
        }
        response.setProducts(productInShoppingList);
        return response;
    }

    public Page<ShoppingList> getShoppingLists(GetShoppingListsRequest request, Pageable pageable) {
        LOGGER.info("Retrieveng lists {}", request);
        if (Objects.nonNull(request) && Objects.nonNull(request.getPartialName())) {
            return shoppingListRepository.findByPartialName(request.getPartialName(), pageable);
        } else {
            return shoppingListRepository.findAll(pageable);
        }
    }

    public void deleteShoppingList(long id) {
        LOGGER.info("Deleting list {}", id);
        shoppingListRepository.deleteById(id);
        LOGGER.info("Deleted list {}", id);
    }
}