package org.fasttrackit.shoppinglist.transfer.productRequests;

public class ProductResponse {
    private Long id;
    private String Name;
    private Boolean isBought;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getBought() {
        return isBought;
    }

    public void setBought(Boolean bought) {
        isBought = bought;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", isBought=" + isBought +
                ", price=" + price +
                '}';
    }
}
