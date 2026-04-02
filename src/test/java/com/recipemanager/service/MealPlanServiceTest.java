package com.recipemanager.service;

import com.recipemanager.model.MealType;
import com.recipemanager.model.Recipe;
import com.recipemanager.store.InMemoryRecipeStore;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MealPlanServiceTest {

    @Test
    void assign_shouldStoreAndReturnRecipeForSlot() {
        MealPlanService mealPlanService = new MealPlanService();
        Recipe recipe = new InMemoryRecipeStore().findAll().get(0);

        mealPlanService.assign(DayOfWeek.MONDAY, MealType.BREAKFAST, recipe);

        Recipe stored = mealPlanService.get(DayOfWeek.MONDAY, MealType.BREAKFAST);
        assertEquals(recipe.getId(), stored.getId());
    }

    @Test
    void assignNull_shouldClearAssignedRecipe() {
        MealPlanService mealPlanService = new MealPlanService();
        Recipe recipe = new InMemoryRecipeStore().findAll().get(0);

        mealPlanService.assign(DayOfWeek.TUESDAY, MealType.DINNER, recipe);
        mealPlanService.assign(DayOfWeek.TUESDAY, MealType.DINNER, null);

        assertNull(mealPlanService.get(DayOfWeek.TUESDAY, MealType.DINNER));
    }
}
