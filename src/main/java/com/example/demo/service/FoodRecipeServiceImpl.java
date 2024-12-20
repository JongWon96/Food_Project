package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.FoodRecipe;
import com.example.demo.persistence.FoodRecipeRepository;

@Service
public class FoodRecipeServiceImpl implements FoodRecipeService{

	@Autowired 
	private FoodRecipeRepository foodRecipeRepo;
	
	@Override
	public FoodRecipe getFoodRecipe(String frName) {

		return foodRecipeRepo.findFoodRecipeByrfNameContaining(frName);
	}

	@Override
	public FoodRecipe getFoodRecipeById(int frUid) {
		
		return foodRecipeRepo.findById(frUid).get();
	}
}
