package org.fasttrackit.shoppinglist.transfer;

public class RemoveFromListRequest {
    private Long productID;
    private Long listID;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getListID() {
        return listID;
    }

    public void setListID(Long listID) {
        this.listID = listID;
    }

    @Override
    public String toString() {
        return "RemoveFromListRequest{" +
                "productID=" + productID +
                ", listID=" + listID +
                '}';
    }
}
