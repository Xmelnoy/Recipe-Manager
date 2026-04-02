package com.recipemanager.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class RecipeTest {

    @Test
    void copy_shouldDeepCopyIngredients() {
        Recipe source = new Recipe(10L, "Pancakes", List.of(
                new Ingredient(1L, "Flour", "Bakery", 100.0, "g")
        ), "Mix and fry", 2);

        Recipe copy = source.copy();

        assertNotSame(source, copy);
        assertEquals(source.getId(), copy.getId());
        assertEquals(source.getName(), copy.getName());
        assertEquals(source.getInstruction(), copy.getInstruction());
        assertEquals(source.getServings(), copy.getServings());
        assertNotSame(source.getIngredients().get(0), copy.getIngredients().get(0));
        assertEquals(source.getIngredients().get(0).getName(), copy.getIngredients().get(0).getName());
    }

    @Test
    void toString_shouldFallbackForMissingName() {
        Recipe recipe = new Recipe();

        assertEquals("Untitled recipe", recipe.toString());

        recipe.setName("Soup");
        assertEquals("Soup", recipe.toString());
    }
}
