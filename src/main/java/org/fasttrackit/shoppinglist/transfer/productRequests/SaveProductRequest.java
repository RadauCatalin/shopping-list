package org.fasttrackit.shoppinglist.transfer.productRequests;

import javax.validation.constraints.NotNull;

public class SaveProductRequest {
    @NotNull
    private String name;
    private Double price;
    private Boolean isBought;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    @Override
    public String toString() {
        return "SaveProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isBought=" + isBought +
                '}';
    }
}
