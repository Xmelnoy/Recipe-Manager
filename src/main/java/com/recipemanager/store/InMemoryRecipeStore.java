package com.recipemanager.store;

import com.recipemanager.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryRecipeStore {

    private final List<Recipe> recipes = new ArrayList<>();
    private final AtomicLong sequence = new AtomicLong(100);

    public InMemoryRecipeStore() {
        recipes.addAll(DemoDataFactory.createRecipes());
    }

    public synchronized List<Recipe> findAll() {
        return recipes.stream().map(Recipe::copy).toList();
    }

    public synchronized Optional<Recipe> findById(Long id) {
        return recipes.stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst()
                .map(Recipe::copy);
    }

    public synchronized Recipe save(Recipe recipe) {
        if (recipe.getId() == null) {
            recipe.setId(sequence.incrementAndGet());
            Recipe stored = recipe.copy();
            recipes.add(stored);
            return stored.copy();
        }

        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getId().equals(recipe.getId())) {
                Recipe stored = recipe.copy();
                recipes.set(i, stored);
                return stored.copy();
            }
        }

        Recipe stored = recipe.copy();
        recipes.add(stored);
        return stored.copy();
    }

    public synchronized boolean delete(Long id) {
        return recipes.removeIf(recipe -> recipe.getId().equals(id));
    }
}
