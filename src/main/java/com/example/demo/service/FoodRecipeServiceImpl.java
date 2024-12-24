package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodRecipe;
import com.example.demo.persistence.FoodRecipeRepository;

@Service
public class FoodRecipeServiceImpl implements FoodRecipeService{

	@Autowired 
	private FoodRecipeRepository foodRecipeRepo;

	@Override
	public FoodRecipe getFoodRecipeById(int frUid) {
		
		return foodRecipeRepo.findById(frUid).get();
	}

	@Override
	public Page<FoodRecipe> getAllFoodRecipeList(String rfName, int page, int size) {
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "rfName");
		
		return foodRecipeRepo.findFoodRecipeByrfNameContaining(rfName, paging);
	}
}
