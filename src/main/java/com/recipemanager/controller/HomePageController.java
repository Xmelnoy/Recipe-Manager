package com.recipemanager.controller;

import com.recipemanager.model.MealPlan;
import com.recipemanager.model.ShoppingListItem;
import com.recipemanager.service.MealPlanService;
import com.recipemanager.service.RecipeService;
import com.recipemanager.service.ShoppingListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    private final RecipeService recipeService;
    private final MealPlanService mealPlanService;
    private final ShoppingListService shoppingListService;

    public HomePageController(RecipeService recipeService, MealPlanService mealPlanService, ShoppingListService shoppingListService) {
        this.recipeService = recipeService;
        this.mealPlanService = mealPlanService;
        this.shoppingListService = shoppingListService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        List<MealPlan> plans = mealPlanService.getAll();
        List<ShoppingListItem> shoppingItems = shoppingListService.fromMealPlans(plans);

        model.addAttribute("recipesCount", recipeService.getAll().size());
        model.addAttribute("plannedMealsCount", plans.stream().filter(plan -> plan.getRecipe() != null).count());
        model.addAttribute("shoppingItemsCount", shoppingItems.size());
        model.addAttribute("latestRecipes", recipeService.getAll().stream().limit(6).toList());
        model.addAttribute("activePage", "dashboard");
        return "dashboard";
    }
}
