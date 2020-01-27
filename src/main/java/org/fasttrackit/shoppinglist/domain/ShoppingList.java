package org.fasttrackit.shoppinglist.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue
    @NotNull
    private long id;
    @NotNull
    private String name;
    private String description;
    private double budget;
    private double remainingBudget;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "shoppingList_product", joinColumns = @JoinColumn(name = "shoppingList_id"),
            inverseJoinColumns = @JoinColumn(name = "product_Id"))
    private Set<Product> products = new HashSet<>();

    public void addToShoppingList(Product product) {
        products.add(product);
        product.getShoppingLists().add(this);
    }

    public void removeFromShoppingList(Product product) {
        products.remove(product);
        product.getShoppingLists().remove(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", remainingBudget=" + remainingBudget +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingList that = (ShoppingList) o;

        if (id != that.id) return false;
        if (Double.compare(that.budget, budget) != 0) return false;
        if (Double.compare(that.remainingBudget, remainingBudget) != 0) return false;
        if (!name.equals(that.name)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(budget);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(remainingBudget);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
