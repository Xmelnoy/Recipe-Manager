package com.recipemanager.controller;

import com.recipemanager.model.Ingredient;
import com.recipemanager.model.Recipe;
import com.recipemanager.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipePageController {

    private final RecipeService recipeService;

    public RecipePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String list(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "category", required = false) String category,
            Model model
    ) {
        String normalizedQuery = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        String normalizedCategory = category == null ? "" : category.trim().toLowerCase(Locale.ROOT);

        List<Recipe> recipes = recipeService.getAll().stream()
                .filter(recipe -> normalizedQuery.isBlank()
                        || recipe.getName().toLowerCase(Locale.ROOT).contains(normalizedQuery)
                        || recipe.getIngredients().stream()
                        .map(Ingredient::getName)
                        .map(name -> name.toLowerCase(Locale.ROOT))
                        .anyMatch(name -> name.contains(normalizedQuery)))
                .filter(recipe -> normalizedCategory.isBlank()
                        || recipe.getIngredients().stream()
                        .map(Ingredient::getCategory)
                        .map(value -> value == null ? "" : value.toLowerCase(Locale.ROOT))
                        .anyMatch(value -> value.contains(normalizedCategory)))
                .toList();

        model.addAttribute("recipes", recipes);
        model.addAttribute("query", query == null ? "" : query);
        model.addAttribute("category", category == null ? "" : category);
        model.addAttribute("activePage", "recipes");
        return "recipes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("recipeForm", RecipeForm.fromRecipe(new Recipe()));
        model.addAttribute("isEdit", false);
        model.addAttribute("activePage", "recipes");
        return "recipes/form";
    }

    @PostMapping
    public String create(@ModelAttribute RecipeForm recipeForm) {
        recipeService.save(recipeForm.toRecipe(null));
        return "redirect:/recipes";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id,
                          @RequestParam(name = "servings", required = false) Integer servings,
                          Model model) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        if (recipeOptional.isEmpty()) {
            return "redirect:/recipes";
        }

        Recipe recipe = recipeOptional.get();
        int targetServings = servings == null || servings < 1 ? recipe.getServings() : servings;
        double factor = (double) targetServings / recipe.getServings();
        List<Ingredient> scaledIngredients = recipe.getIngredients().stream().map(ingredient -> ingredient.scaled(factor)).toList();

        model.addAttribute("recipe", recipe);
        model.addAttribute("scaledIngredients", scaledIngredients);
        model.addAttribute("targetServings", targetServings);
        model.addAttribute("activePage", "recipes");
        return "recipes/details";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeService.getById(id);
        if (recipeOptional.isEmpty()) {
            return "redirect:/recipes";
        }

        model.addAttribute("recipeForm", RecipeForm.fromRecipe(recipeOptional.get()));
        model.addAttribute("isEdit", true);
        model.addAttribute("recipeId", id);
        model.addAttribute("activePage", "recipes");
        return "recipes/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute RecipeForm recipeForm) {
        recipeService.save(recipeForm.toRecipe(id));
        return "redirect:/recipes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.delete(id);
        return "redirect:/recipes";
    }

    public static class RecipeForm {
        private String name;
        private String instruction;
        private Integer servings;
        private String ingredientsText;

        public static RecipeForm fromRecipe(Recipe recipe) {
            RecipeForm form = new RecipeForm();
            form.setName(recipe.getName() == null ? "" : recipe.getName());
            form.setInstruction(recipe.getInstruction() == null ? "" : recipe.getInstruction());
            form.setServings(recipe.getServings() <= 0 ? 1 : recipe.getServings());
            form.setIngredientsText(recipe.getIngredients().stream()
                    .map(ingredient -> ingredient.getName() + ";" + ingredient.getCategory() + ";" + ingredient.getAmount() + ";" + ingredient.getUnit())
                    .reduce((left, right) -> left + "\n" + right)
                    .orElse(""));
            return form;
        }

        public Recipe toRecipe(Long id) {
            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setName(name == null ? "" : name.trim());
            recipe.setInstruction(instruction == null ? "" : instruction.trim());
            recipe.setServings(servings == null || servings < 1 ? 1 : servings);
            recipe.setIngredients(parseIngredients(ingredientsText));
            return recipe;
        }

        private List<Ingredient> parseIngredients(String input) {
            if (input == null || input.isBlank()) {
                return List.of();
            }

            String[] lines = input.split("\\R");
            List<Ingredient> items = new ArrayList<>();
            long sequence = 1;

            for (String line : lines) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length < 4) {
                    continue;
                }

                try {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setId(sequence++);
                    ingredient.setName(parts[0].trim());
                    ingredient.setCategory(parts[1].trim());
                    ingredient.setAmount(Double.parseDouble(parts[2].trim().replace(',', '.')));
                    ingredient.setUnit(parts[3].trim());
                    items.add(ingredient);
                } catch (NumberFormatException ignored) {
                }
            }

            return items;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        public Integer getServings() {
            return servings;
        }

        public void setServings(Integer servings) {
            this.servings = servings;
        }

        public String getIngredientsText() {
            return ingredientsText;
        }

        public void setIngredientsText(String ingredientsText) {
            this.ingredientsText = ingredientsText;
        }
    }
}
