package org.fasttrackit.shoppinglist.service;

import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ShoppingListRepository;
import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.AddToShoppingListRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.GetShoppingListsRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.SaveShoppingListRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.ShoppingListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class ShoppingListService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListService.class);

    private final ShoppingListRepository shoppingListRepository;

    @Autowired

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public double calculateBudget(ShoppingList shoppingList) {
       // double budget = shoppingList.getBudget();
        double remainingBudget = shoppingList.getBudget();
        Iterator<Product> products = shoppingList.getProducts().iterator();
        while (products.hasNext()) {
            Product product = products.next();
            remainingBudget = remainingBudget - product.getPrice();
            shoppingList.setRemainingBudget(remainingBudget);
        }
        return remainingBudget;
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


    public void addProductToShoppingList(AddToShoppingListRequest request, Product product) {
        LOGGER.info("Adding product to shopping list {}", request);
        ShoppingList shoppingList = shoppingListRepository.findById(request.getListId()).orElseThrow();
        shoppingList.addToShoppingList(product);
    }

    @Transactional
    public ShoppingListResponse getShoppingList(long id) {
        LOGGER.info("Retrieving list {}", id);
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("List " + id + " does not exist. "));
        ShoppingListResponse response = new ShoppingListResponse();
        response.setId(shoppingList.getId());
        response.setName(shoppingList.getName());
        response.setBudget(shoppingList.getBudget());
        response.setRemainingBudget(calculateBudget(shoppingList));
        response.setDescription(shoppingList.getDescription());

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

    public Set<GetShoppingListsRequest> getShoppingLists(GetShoppingListsRequest request, Pageable pageable) {
        LOGGER.info("Retrieveng lists {}", request);
        Set<GetShoppingListsRequest> listsRequests = new HashSet<>();
        Iterator<ShoppingList> listIterator = shoppingListRepository.findAll().iterator();
        while (listIterator.hasNext()) {
            ShoppingList shoppingList = listIterator.next();

            GetShoppingListsRequest getShoppingListsRequest = new GetShoppingListsRequest();
            getShoppingListsRequest.setId(shoppingList.getId());
            getShoppingListsRequest.setName(shoppingList.getName());

            listsRequests.add(getShoppingListsRequest);
        }

        return listsRequests;
    }

    public ShoppingListResponse updateShoppingList(long id, SaveShoppingListRequest request) {
        LOGGER.info("Updating shopping list id {}: {}", id, request);
        ShoppingListResponse shoppingListResponse = getShoppingList(id);
        BeanUtils.copyProperties(request, shoppingListResponse);
        return shoppingListResponse;
    }

    public void deleteShoppingList(long id) {
        LOGGER.info("Deleting list {}", id);
        shoppingListRepository.deleteById(id);
        LOGGER.info("Deleted list {}", id);
    }
}