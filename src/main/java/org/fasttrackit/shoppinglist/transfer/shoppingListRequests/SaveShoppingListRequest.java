package org.fasttrackit.shoppinglist.transfer.shoppingListRequests;

import javax.validation.constraints.NotNull;

public class SaveShoppingListRequest {
    @NotNull
    private String name;
    private String description;
    private Double budget;
    private Double remainingBudget;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(double remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    @Override
    public String toString() {
        return "SaveShoppingListRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", remainingBudget=" + remainingBudget +
                '}';
    }
}
