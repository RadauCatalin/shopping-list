package org.fasttrackit.shoppinglist.transfer;

public class GetShoppingListsRequest {
    private String partialName;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    @Override
    public String toString() {
        return "GetShoppingListsRequest{" +
                "partialName='" + partialName + '\'' +
                '}';
    }
}
