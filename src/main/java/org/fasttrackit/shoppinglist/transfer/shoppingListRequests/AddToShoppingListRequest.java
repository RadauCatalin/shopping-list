package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

import javax.validation.constraints.NotNull;

public class AddToShoppingListRequest {
    @NotNull
    private Long productId;
    @NotNull
    private Long listId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "AddToShoppingListRequest{" +
                "productId=" + productId +
                ", listId=" + listId +
                '}';
    }
}
