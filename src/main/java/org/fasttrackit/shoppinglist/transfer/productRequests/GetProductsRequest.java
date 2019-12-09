package org.fasttrackit.shoppinglist.transfer.productRequests;

public class GetProductsRequest {
    private String partialName;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    @Override
    public String toString() {
        return "GetProductRequest{" +
                "partialName='" + partialName + '\'' +
                '}';
    }
}
