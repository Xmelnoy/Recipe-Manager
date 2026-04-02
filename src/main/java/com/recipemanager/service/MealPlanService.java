package com.recipemanager.service;

import com.recipemanager.model.MealPlan;
import com.recipemanager.model.MealType;
import com.recipemanager.model.Recipe;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MealPlanService {

    private final Map<DayOfWeek, Map<MealType, Recipe>> plan = new EnumMap<>(DayOfWeek.class);
    private final AtomicLong idSequence = new AtomicLong(1);

    public MealPlanService() {
        for (DayOfWeek day : DayOfWeek.values()) {
            plan.put(day, new EnumMap<>(MealType.class));
        }
    }

    public List<MealPlan> getAll() {
        List<MealPlan> rows = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            for (MealType mealType : MealType.values()) {
                Recipe recipe = plan.get(day).get(mealType);
                rows.add(new MealPlan(idSequence.getAndIncrement(), day, mealType, recipe));
            }
        }
        return rows;
    }

    public Recipe get(DayOfWeek day, MealType mealType) {
        return plan.get(day).get(mealType);
    }

    public void assign(DayOfWeek day, MealType mealType, Recipe recipe) {
        if (recipe == null) {
            plan.get(day).remove(mealType);
            return;
        }
        plan.get(day).put(mealType, recipe.copy());
    }
}
