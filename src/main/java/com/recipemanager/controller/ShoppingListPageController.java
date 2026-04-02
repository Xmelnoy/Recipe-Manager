package com.recipemanager.controller;

import com.recipemanager.model.ShoppingListItem;
import com.recipemanager.service.MealPlanService;
import com.recipemanager.service.ShoppingListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shopping-list")
public class ShoppingListPageController {

    private final ShoppingListService shoppingListService;
    private final MealPlanService mealPlanService;

    public ShoppingListPageController(ShoppingListService shoppingListService, MealPlanService mealPlanService) {
        this.shoppingListService = shoppingListService;
        this.mealPlanService = mealPlanService;
    }

    @GetMapping
    public String page(Model model) {
        List<ShoppingListItem> items = shoppingListService.fromMealPlans(mealPlanService.getAll());
        model.addAttribute("items", items);
        model.addAttribute("activePage", "shopping-list");
        return "shopping-list";
    }
}
