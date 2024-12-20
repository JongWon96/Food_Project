package com.example.demo.service;

import com.example.demo.domain.FoodRecipe;

public interface FoodRecipeService {

	public FoodRecipe getFoodRecipe(String frName);
	
	public FoodRecipe getFoodRecipeById(int frUid);
}
