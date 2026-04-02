package com.recipemanager.controller;

import com.recipemanager.model.MealPlan;
import com.recipemanager.model.MealType;
import com.recipemanager.model.Recipe;
import com.recipemanager.service.MealPlanService;
import com.recipemanager.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meal-plan")
public class MealPlanPageController {

    private final MealPlanService mealPlanService;
    private final RecipeService recipeService;

    public MealPlanPageController(MealPlanService mealPlanService, RecipeService recipeService) {
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public String page(Model model) {
        List<Recipe> recipes = recipeService.getAll();
        List<MealPlan> planRows = mealPlanService.getAll();

        Map<DayOfWeek, Map<MealType, Recipe>> grid = new EnumMap<>(DayOfWeek.class);
        for (DayOfWeek day : DayOfWeek.values()) {
            grid.put(day, new EnumMap<>(MealType.class));
        }
        for (MealPlan row : planRows) {
            grid.get(row.getDayOfWeek()).put(row.getMealType(), row.getRecipe());
        }

        model.addAttribute("days", DayOfWeek.values());
        model.addAttribute("mealTypes", MealType.values());
        model.addAttribute("recipes", recipes);
        model.addAttribute("grid", grid);
        model.addAttribute("activePage", "meal-plan");
        return "meal-plan";
    }

    @PostMapping("/assign")
    public String assign(
            @RequestParam("day") DayOfWeek day,
            @RequestParam("mealType") MealType mealType,
            @RequestParam(name = "recipeId", required = false) Long recipeId
    ) {
        Recipe recipe = recipeId == null
                ? null
                : recipeService.getById(recipeId).orElse(null);

        mealPlanService.assign(day, mealType, recipe);
        return "redirect:/meal-plan";
    }
}
