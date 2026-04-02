package com.recipemanager.model;

public class ShoppingListItem {
    private String ingredientName;
    private double totalAmount;
    private String unit;
    private String category;

    public ShoppingListItem() {
    }

    public ShoppingListItem(String ingredientName, double totalAmount, String unit, String category) {
        this.ingredientName = ingredientName;
        this.totalAmount = totalAmount;
        this.unit = unit;
        this.category = category;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
