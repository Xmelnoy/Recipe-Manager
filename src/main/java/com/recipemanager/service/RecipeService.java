package com.recipemanager.service;

import com.recipemanager.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    public List<Recipe> getAll() {
        return List.of(
                new Recipe(1L, "Омлет", "Быстрый завтрак из яиц"),
                new Recipe(2L, "Паста", "Простой ужин за 20 минут")
        );
    }
}
