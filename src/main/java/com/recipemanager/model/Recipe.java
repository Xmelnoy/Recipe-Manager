package com.recipemanager.model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private Long id;
	private String name;
	private List<Ingredient> ingredients;
	private String instruction;
	private int servings;

	public Recipe() {
		this.ingredients = new ArrayList<>();
		this.servings = 1;
	}

	public Recipe(Long id, String name, List<Ingredient> ingredients, String instruction, int servings) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients == null ? new ArrayList<>() : new ArrayList<>(ingredients);
		this.instruction = instruction;
		this.servings = servings;
	}

	public Recipe copy() {
		List<Ingredient> copiedIngredients = ingredients.stream().map(Ingredient::copy).toList();
		return new Recipe(id, name, copiedIngredients, instruction, servings);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients == null ? new ArrayList<>() : new ArrayList<>(ingredients);
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	@Override
	public String toString() {
		return name == null ? "Untitled recipe" : name;
	}
}
