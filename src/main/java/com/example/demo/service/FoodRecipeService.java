package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodRecipe;

public interface FoodRecipeService {
	
	public FoodRecipe getFoodRecipeById(int frUid);
	
	public Page<FoodRecipe> getAllFoodRecipeList(String rfName, int page, int size);
}
