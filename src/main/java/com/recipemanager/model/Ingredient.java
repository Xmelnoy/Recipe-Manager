package com.recipemanager.model;

public class Ingredient {
    private Long id;
    private String name;
    private String category;
    private double amount;
    private String unit;

    public Ingredient() {
    }

    public Ingredient(Long id, String name, String category, double amount, String unit) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.unit = unit;
    }

    public Ingredient copy() {
        return new Ingredient(id, name, category, amount, unit);
    }

    public Ingredient scaled(double factor) {
        return new Ingredient(id, name, category, amount * factor, unit);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name + " - " + amount + " " + unit + " (" + category + ")";
    }
}
