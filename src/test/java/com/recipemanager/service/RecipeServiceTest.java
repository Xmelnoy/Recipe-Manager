package com.recipemanager.service;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.Recipe;
import com.recipemanager.store.InMemoryRecipeStore;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeServiceTest {

    @Test
    void searchByIngredient_shouldFindMatchingRecipesCaseInsensitive() {
        RecipeService service = new RecipeService(new InMemoryRecipeStore());

        List<Recipe> result = service.searchByIngredient("avoca");

        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(recipe -> "Avocado Toast".equals(recipe.getName())));
    }

    @Test
    void saveAndDelete_shouldChangeDataSet() {
        RecipeService service = new RecipeService(new InMemoryRecipeStore());
        int before = service.getAll().size();

        Recipe saved = service.save(new Recipe(null, "Test", List.of(
                new Ingredient(null, "Salt", "Pantry", 1, "tsp")
        ), "Mix", 1));

        assertEquals(before + 1, service.getAll().size());
        assertTrue(service.delete(saved.getId()));
        assertEquals(before, service.getAll().size());
    }
}
