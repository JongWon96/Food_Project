package com.example.demo.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.FoodRecipe;

public interface FoodRecipeRepository extends JpaRepository<FoodRecipe, Integer>{

	
	public Page<FoodRecipe> findFoodRecipeByrfNameContaining(String rfName, Pageable pageable);
	
}
