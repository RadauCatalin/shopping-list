package org.fasttrackit.shoppinglist.web;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.service.ShoppingListService;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.GetShoppingListsRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.SaveShoppingListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/shoppingLists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody @Valid SaveShoppingListRequest request) {
        ShoppingList shoppingList = shoppingListService.createShoppingList(request);
        return new ResponseEntity<>(shoppingList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable("id") Long id) {
        ShoppingList shoppingList = shoppingListService.getShoppingList(id);
        return new ResponseEntity<>(shoppingList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ShoppingList>> getShoppingLists(GetShoppingListsRequest request, Pageable pageable) {
        Page<ShoppingList> shoppingLists = shoppingListService.getShoppingLists(request, pageable);
        return new ResponseEntity<>(shoppingLists, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable("id") Long id, @RequestBody @Valid SaveShoppingListRequest request) {
        ShoppingList shoppingList = shoppingListService.updateShoppingList(id, request);
        return new ResponseEntity<>(shoppingList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShoppingList(@PathVariable("id") Long id) {
        shoppingListService.deleteShoppingList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}