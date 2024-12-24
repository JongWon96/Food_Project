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
	
	public Page<Food> getAllFoodListByCategory(String category, int page, int size){
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "fcategory");
		
		return foodRepo.findAllFoodByfcategoryContaining(category, paging);
	}

	@Override
	public Page<Food> getAllFoodList(String FName, int page, int size) {
		// page 번호는 0부터 시작함. 이름(fname)순으로 정렬
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "fname");
		
		return foodRepo.findAllFoodsByfnameContaining(FName, paging);
	}

	@Override
	public List<Food> getFoodListByCategory(String category) {
		
		return foodRepo.findAllFoodsByfcategoryContaining(category);
	}

	@Override
	public Food getFood(int fUid) {
		
		return foodRepo.findById(fUid).get();
	}

	@Override
	public Page<Food> getFoodListBySearch(String category, String SearchWord, int page, int size) {
		Pageable paging = PageRequest.of(page-1, size, Direction.ASC, "fname");
		
		return foodRepo.findByFnameContainingAndFcategoryContaining(category, SearchWord, paging);
	}


}








