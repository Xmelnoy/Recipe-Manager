package com.recipemanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class IngredientTest {

    @Test
    void scaled_shouldMultiplyAmountAndKeepMetadata() {
        Ingredient ingredient = new Ingredient(1L, "Rice", "Grains", 200.0, "g");

        Ingredient scaled = ingredient.scaled(1.5);

        assertEquals(300.0, scaled.getAmount());
        assertEquals("Rice", scaled.getName());
        assertEquals("Grains", scaled.getCategory());
        assertEquals("g", scaled.getUnit());
    }

    @Test
    void copy_shouldCreateNewInstanceWithSameValues() {
        Ingredient ingredient = new Ingredient(2L, "Milk", "Dairy", 0.5, "l");

        Ingredient copy = ingredient.copy();

        assertNotSame(ingredient, copy);
        assertEquals(ingredient.getId(), copy.getId());
        assertEquals(ingredient.getName(), copy.getName());
        assertEquals(ingredient.getCategory(), copy.getCategory());
        assertEquals(ingredient.getAmount(), copy.getAmount());
        assertEquals(ingredient.getUnit(), copy.getUnit());
    }
}
