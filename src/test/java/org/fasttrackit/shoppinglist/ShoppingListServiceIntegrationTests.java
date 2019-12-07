package org.fasttrackit.shoppinglist;

import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.service.ShoppingListService;
import org.fasttrackit.shoppinglist.transfer.SaveShoppingListRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingListServiceIntegrationTests {

    @Autowired
    private ShoppingListService shoppingListService;

    @Test
    public void testCreateShoppingList_whenValidRequest_thenProductisSaved() {
        createShoppingList();
    }

    @Test(expected = TransactionSystemException.class)
    public void testCreateShoppingList_whenInvalidRequest_thenThrowException() {
        SaveShoppingListRequest request = new SaveShoppingListRequest();

        shoppingListService.createShoppingList(request);
    }

    private ShoppingList createShoppingList() {
        SaveShoppingListRequest request = new SaveShoppingListRequest();
        request.setName("Kaufland" + System.currentTimeMillis());
        request.setDescription("For Christmas dinner");
        request.setBudget(1600);
        request.setRemainingBudget(1600);

        ShoppingList createdShoppingList = shoppingListService.createShoppingList(request);

        assertThat(createdShoppingList, notNullValue());
        assertThat(createdShoppingList.getId(), notNullValue());
        assertThat(createdShoppingList.getId(), greaterThan(0L));
        assertThat(createdShoppingList.getName(), is(request.getName()));
        assertThat(createdShoppingList.getDescription(), is(request.getDescription()));
        assertThat(createdShoppingList.getBudget(), is(request.getBudget()));
        assertThat(createdShoppingList.getRemainingBudget(), is(request.getRemainingBudget()));

        return createdShoppingList;
    }


}
