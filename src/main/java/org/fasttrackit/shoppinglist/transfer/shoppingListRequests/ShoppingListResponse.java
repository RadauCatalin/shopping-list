package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;

import java.util.Set;

public class ShoppingListResponse {
    private Long id;
    private String name;
    private Double budget;
    private Double remainingBudget;
    private String description;
    private Set<ProductResponse> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", budget=" + budget +
                ", remainingBudget=" + remainingBudget +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}
