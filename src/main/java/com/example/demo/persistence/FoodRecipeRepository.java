package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.FoodRecipe;

public interface FoodRecipeRepository extends JpaRepository<FoodRecipe, Integer>{

	public FoodRecipe findFoodRecipeByrfNameContaining(String rfName);
	
}
