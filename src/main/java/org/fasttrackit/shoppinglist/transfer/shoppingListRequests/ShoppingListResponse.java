package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;

import java.util.Set;

public class ShoppingListResponse {
    private Long id;
    private Set<ProductResponse> products;

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
                ", products=" + products +
                '}';
    }
}
