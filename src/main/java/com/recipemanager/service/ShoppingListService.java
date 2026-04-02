package com.recipemanager.service;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.MealPlan;
import com.recipemanager.model.Recipe;
import com.recipemanager.model.ShoppingListItem;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ShoppingListService {

    public List<ShoppingListItem> fromMealPlans(List<MealPlan> plans) {
        Map<String, ShoppingListItem> merged = new HashMap<>();

        for (MealPlan plan : plans) {
            Recipe recipe = plan.getRecipe();
            if (recipe == null) {
                continue;
            }
            for (Ingredient ingredient : recipe.getIngredients()) {
                String key = (ingredient.getName() + "|" + ingredient.getUnit() + "|" + ingredient.getCategory())
                        .toLowerCase(Locale.ROOT);

                merged.compute(key, (k, existing) -> {
                    if (existing == null) {
                        return new ShoppingListItem(
                                ingredient.getName(),
                                ingredient.getAmount(),
                                ingredient.getUnit(),
                                ingredient.getCategory()
                        );
                    }
                    existing.setTotalAmount(existing.getTotalAmount() + ingredient.getAmount());
                    return existing;
                });
            }
        }

        return merged.values().stream()
                .sorted(Comparator
                        .comparing(ShoppingListItem::getCategory, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(ShoppingListItem::getIngredientName, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}
