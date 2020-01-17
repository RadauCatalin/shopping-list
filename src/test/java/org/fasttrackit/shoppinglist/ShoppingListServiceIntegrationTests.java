package org.fasttrackit.shoppinglist;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingListServiceIntegrationTests {


 //   @Autowired
//    private ShoppingListService shoppingListService;
//
//    @Test
//    public void testCreateShoppingList_whenValidRequest_thenProductisSaved() {
//        createShoppingList();
//    }
//
//    @Test(expected = TransactionSystemException.class)
//    public void testCreateShoppingList_whenInvalidRequest_thenThrowException() {
//        SaveShoppingListRequest request = new SaveShoppingListRequest();
//
//        shoppingListService.createShoppingList(request);
//    }
//
//    @Test
//    public void testGetShoppingList_whenExistingShoppingList_thenReturnProduct() {
//        ShoppingList createdShoppingList = createShoppingList();
//
//        ShoppingList shoppingList = shoppingListService.getShoppingList(createdShoppingList.getId());
//
//        assertThat(shoppingList, notNullValue());
//        assertThat(shoppingList.getId(), is(createdShoppingList.getId()));
//        assertThat(shoppingList.getName(), is(createdShoppingList.getName()));
//        assertThat(shoppingList.getDescription(), is(createdShoppingList.getDescription()));
//        assertThat(shoppingList.getBudget(), is(createdShoppingList.getBudget()));
//        assertThat(shoppingList.getRemainingBudget(), is(createdShoppingList.getRemainingBudget()));
//    }
//
//    @Test(expected = ResourceNotFoundException.class)
//    public void testGetShoppingList_WhenNonExistingShoppingList_thenThrowResourceNotFoundException() {
//        shoppingListService.getShoppingList(99999999999999L);
//    }
//
//    @Test
//    public void testUpdateShoppingList_whenValidRequest_thanReturnUpdatedProduct() {
//        ShoppingList createdShoppingList = createShoppingList();
//
//        SaveShoppingListRequest request = new SaveShoppingListRequest();
//        request.setName(createdShoppingList.getName() + "Updated");
//        request.setDescription(createdShoppingList.getDescription() + "updated");
//        request.setBudget(createdShoppingList.getBudget() + 23.00);
//        request.setRemainingBudget(createdShoppingList.getRemainingBudget() + 5.00);
//
//        ShoppingList updatedShoppingList = shoppingListService.updateShoppingList(createdShoppingList.getId(), request);
//
//        assertThat(updatedShoppingList, notNullValue());
//        assertThat(updatedShoppingList.getId(), is(createdShoppingList.getId()));
//        assertThat(updatedShoppingList.getName(), is(request.getName()));
//        assertThat(updatedShoppingList.getDescription(), is(request.getDescription()));
//        assertThat(updatedShoppingList.getBudget(), is(request.getBudget()));
//        assertThat(updatedShoppingList.getRemainingBudget(), is(request.getRemainingBudget()));
//    }
//
//    @Test(expected = ResourceNotFoundException.class)
//    public void testDeleteShoppingList_whenExistingShoppingList_thenShoppingListIsDeketed() {
//        ShoppingList shoppingList = createShoppingList();
//
//        shoppingListService.deleteShoppingList(shoppingList.getId());
//        shoppingListService.getShoppingList(shoppingList.getId());
//    }
//
//    private ShoppingList createShoppingList() {
//
//        SaveShoppingListRequest request = new SaveShoppingListRequest();
//        request.setName("Kaufland" + System.currentTimeMillis());
//        request.setDescription("For Christmas dinner");
//        request.setBudget(1600.30);
//        request.setRemainingBudget(1600.40);
//
//        ShoppingList createdShoppingList = shoppingListService.createShoppingList(request);
//
//        assertThat(createdShoppingList, notNullValue());
//        assertThat(createdShoppingList.getId(), notNullValue());
//        assertThat(createdShoppingList.getId(), greaterThan(0L));
//        assertThat(createdShoppingList.getName(), is(request.getName()));
//        assertThat(createdShoppingList.getDescription(), is(request.getDescription()));
//        assertThat(createdShoppingList.getBudget(), is(request.getBudget()));
//        assertThat(createdShoppingList.getRemainingBudget(), is(request.getRemainingBudget()));
//
//        return createdShoppingList;
//    }
}