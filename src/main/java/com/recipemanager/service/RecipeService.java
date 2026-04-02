package com.recipemanager.service;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.Recipe;
import com.recipemanager.store.InMemoryRecipeStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class RecipeService {

    private final InMemoryRecipeStore store;

    public RecipeService(InMemoryRecipeStore store) {
        this.store = store;
    }

    public List<Recipe> getAll() {
        return store.findAll();
    }

    public Optional<Recipe> getById(Long id) {
        return store.findById(id);
    }

    public Recipe save(Recipe recipe) {
        return store.save(recipe);
    }

    public boolean delete(Long id) {
        return store.delete(id);
    }

    public List<Recipe> searchByIngredient(String query) {
        String normalized = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        if (normalized.isBlank()) {
            return getAll();
        }

        return getAll().stream()
                .filter(recipe -> recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .filter(name -> name != null)
                        .map(name -> name.toLowerCase(Locale.ROOT))
                        .anyMatch(name -> name.contains(normalized)))
                .toList();
    }
}
