package com.recipemanager.model;

import java.time.DayOfWeek;

public class MealPlan {
    private Long id;
    private DayOfWeek dayOfWeek;
    private MealType mealType;
    private Recipe recipe;

    public MealPlan() {
    }

    public MealPlan(Long id, DayOfWeek dayOfWeek, MealType mealType, Recipe recipe) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.mealType = mealType;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
