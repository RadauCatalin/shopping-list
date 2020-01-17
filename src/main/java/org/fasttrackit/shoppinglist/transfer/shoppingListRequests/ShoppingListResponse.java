package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;

import java.util.Set;

public class ShoppingListResponse {
    private Long id;
    private String name;
    private Double remainingBudget;
    private Set<ProductResponse> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(Double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductResponse> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingListResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remainingBudget=" + remainingBudget +
                ", products=" + products +
                '}';
    }
}
