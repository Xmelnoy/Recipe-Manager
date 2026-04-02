package com.recipemanager.store;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryRecipeStoreTest {

    @Test
    void saveNewRecipe_shouldAssignIdAndPersist() {
        InMemoryRecipeStore store = new InMemoryRecipeStore();
        Recipe recipe = new Recipe(null, "New Recipe", List.of(
                new Ingredient(null, "Tomato", "Produce", 2, "pcs")
        ), "Cut", 1);

        Recipe saved = store.save(recipe);

        assertTrue(saved.getId() != null);
        assertTrue(store.findById(saved.getId()).isPresent());
    }

    @Test
    void delete_shouldRemoveRecipeById() {
        InMemoryRecipeStore store = new InMemoryRecipeStore();
        Long existingId = store.findAll().get(0).getId();

        boolean removed = store.delete(existingId);

        assertTrue(removed);
        assertFalse(store.findById(existingId).isPresent());
    }

    @Test
    void findAll_shouldReturnDefensiveCopies() {
        InMemoryRecipeStore store = new InMemoryRecipeStore();

        List<Recipe> firstRead = store.findAll();
        firstRead.get(0).setName("Mutated");

        List<Recipe> secondRead = store.findAll();
        assertFalse("Mutated".equals(secondRead.get(0).getName()));
        assertEquals(4, secondRead.size());
    }
}
