package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;
import com.example.demo.domain.FoodRecipe;
import com.example.demo.persistence.FoodRecipeRepository;
import com.example.demo.persistence.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodRepository foodRepo;
	@Autowired 
	private FoodRecipeRepository foodRecipeRepo;
	
	public Page<Food> getAllFoodListByCategory(String category, int page, int size){
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "fCategory");
		
		return foodRepo.findAllFoodsByfCategoryContaining(category, paging);
	}

	@Override
	public Page<Food> getAllFoodList(String FName, int page, int size) {
		// page 번호는 0부터 시작함. 제품명(name)순으로 정렬
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "fName");
		
		return foodRepo.findAllFoodsByfNameContaining(FName, paging);
	}

	@Override
	public List<Food> getFoodListByCategory(String category) {
		
		return foodRepo.findAllFoodByfCategoryContaining(category);
	}

	@Override
	public Food getFood(int fUid) {
		
		return foodRepo.findById(fUid).get();
	}

	@Override
	public FoodDetail getFoodDetail(int fUid) {
		
		return foodRepo.findFoodDetailByfUid(fUid);
	}

	@Override
	public FoodRecipe getFoodRecipe(String frName) {

		return foodRecipeRepo.findFoodRecipeByrfNameContaining(frName);
	}

	
}
