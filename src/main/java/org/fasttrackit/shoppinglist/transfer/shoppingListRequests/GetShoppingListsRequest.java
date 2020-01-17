package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

public class GetShoppingListsRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetShoppingListsRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
