package org.fasttrackit.shoppinglist.transfer.productRequests;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

public class SaveProductRequest {
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    private Double price;
    private Boolean isBought;
    private Long listId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getBought() {
        return isBought;
    }

    public void setBought(Boolean bought) {
        isBought = bought;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    @Override
    public String toString() {
        return "SaveProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isBought=" + isBought +
                ", listId=" + listId +
                '}';
    }
}
