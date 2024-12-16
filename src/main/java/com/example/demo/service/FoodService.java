package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;
import com.example.demo.domain.FoodRecipe;

public interface FoodService {
	
	public Page<Food> getAllFoodListByCategory(String category, int page, int size);
	
	public List<Food> getFoodListByCategory(String category);
	
	public Page<Food> getAllFoodList(String FName, int page, int size);
	
	public Food getFood(int fUid);

	public FoodDetail getFoodDetail(int fUid);
	
	public FoodRecipe getFoodRecipe(String frName);

}
