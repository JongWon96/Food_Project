package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;
import com.example.demo.domain.FoodRecipe;

public interface FoodRepository extends JpaRepository<Food, Integer>{

	// 음식 카테고리별 조회
	public List<Food> findAllFoodByfCategoryContaining(String category);
	
	// 카테고리별 페이징 조회
	public Page<Food> findAllFoodsByfCategoryContaining(String FName, Pageable pageable);
		
	// 
	// List<Food> findAllFoodByfNameContaining(String FName);
	
	// 전체음식 조회(상품명으로 검색 포함)
	public List<Food> findAllFoodByfNameContaining(String FName);
	
	// 전체음식 조회(페이징 처리, 상품명으로 검색 포함)
	public Page<Food> findAllFoodsByfNameContaining(String FName, Pageable pageable);

	
//	@Query("SELECT fd from FoodDetail fd " 
//			+ " INNER JOIN Food f on f.fUid = fd.fUid "
//			+ " WHERE fd.fUid = %?1% ")
//  public FoodDetail getFoodDetail (int fUid);
	public FoodDetail findFoodDetailByfUid(int fUid);


}
