package com.recipemanager.service;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.MealPlan;
import com.recipemanager.model.MealType;
import com.recipemanager.model.Recipe;
import com.recipemanager.model.ShoppingListItem;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingListServiceTest {

    @Test
    void fromMealPlans_shouldMergeSameIngredients() {
        Recipe first = new Recipe(1L, "R1", List.of(
                new Ingredient(1L, "Tomato", "Produce", 2, "pcs")
        ), "", 1);
        Recipe second = new Recipe(2L, "R2", List.of(
                new Ingredient(2L, "Tomato", "Produce", 3, "pcs")
        ), "", 1);

        List<MealPlan> plans = List.of(
                new MealPlan(1L, DayOfWeek.MONDAY, MealType.LUNCH, first),
                new MealPlan(2L, DayOfWeek.TUESDAY, MealType.DINNER, second)
        );

        ShoppingListService service = new ShoppingListService();
        List<ShoppingListItem> result = service.fromMealPlans(plans);

        assertEquals(1, result.size());
        assertEquals(5.0, result.get(0).getTotalAmount());
    }

    @Test
    void fromMealPlans_shouldSkipEmptyPlanSlots() {
        List<MealPlan> plans = List.of(
                new MealPlan(1L, DayOfWeek.MONDAY, MealType.BREAKFAST, null)
        );

        ShoppingListService service = new ShoppingListService();
        List<ShoppingListItem> result = service.fromMealPlans(plans);

        assertTrue(result.isEmpty());
    }
}
