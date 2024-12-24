package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDetail;

public interface FoodRepository extends JpaRepository<Food, Integer>{

	// 음식 카테고리 조회
	public List<Food> findAllFoodsByfcategoryContaining(String category);
	
	// 카테고리별 페이징 조회
	public Page<Food> findAllFoodByfcategoryContaining(String category, Pageable pageable);

	// 전체음식 조회(페이징 처리, 상품명으로 검색 포함)
	public Page<Food> findAllFoodsByfnameContaining(String FName, Pageable pageable);

//	@Query("SELECT f FROM Food f "
//			 + " WHERE f.category LIKE %?1% "
//			 + " AND f.fName LIKE %?2% "
//			 + " ORDER BY fName DESC")
	public Page<Food> findByFcategoryContainingAndFnameContaining(String category, String fname, Pageable pageable);
	

}


















