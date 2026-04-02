package com.recipemanager.store;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.Recipe;

import java.util.List;

public final class DemoDataFactory {

    private DemoDataFactory() {
    }

    public static List<Recipe> createRecipes() {
        return List.of(
                new Recipe(1L, "Avocado Toast", List.of(
                        new Ingredient(1L, "Whole grain bread", "Bakery", 2, "slices"),
                        new Ingredient(2L, "Avocado", "Produce", 1, "pcs"),
                        new Ingredient(3L, "Lemon juice", "Produce", 1, "tsp")
                ), "Toast bread, mash avocado with lemon juice, season and spread.", 2),
                new Recipe(2L, "Chicken Bowl", List.of(
                        new Ingredient(4L, "Chicken breast", "Meat", 400, "g"),
                        new Ingredient(5L, "Rice", "Grains", 200, "g"),
                        new Ingredient(6L, "Broccoli", "Produce", 200, "g")
                ), "Cook rice. Grill chicken and steam broccoli. Assemble in bowl.", 3),
                new Recipe(3L, "Pasta Primavera", List.of(
                        new Ingredient(7L, "Pasta", "Grains", 300, "g"),
                        new Ingredient(8L, "Cherry tomatoes", "Produce", 200, "g"),
                        new Ingredient(9L, "Parmesan", "Dairy", 50, "g")
                ), "Boil pasta, saute tomatoes, combine and top with parmesan.", 4),
                new Recipe(4L, "Yogurt Parfait", List.of(
                        new Ingredient(10L, "Greek yogurt", "Dairy", 300, "g"),
                        new Ingredient(11L, "Granola", "Pantry", 100, "g"),
                        new Ingredient(12L, "Blueberries", "Produce", 120, "g")
                ), "Layer yogurt, granola and berries in glass.", 2)
        );
    }
}
