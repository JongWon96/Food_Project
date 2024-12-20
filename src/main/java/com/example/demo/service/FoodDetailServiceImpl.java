package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.FoodDetail;
import com.example.demo.persistence.FoodDetailRepository;

import jakarta.transaction.Transactional;

@Service
public class FoodDetailServiceImpl implements FoodDetailService{

	@Autowired
	private FoodDetailRepository foodDetailRepo;
	
	@Override
	public FoodDetail getFoodDetail(int fUid) {
		System.out.println("service uid: [" + fUid + "]");
		FoodDetail de = foodDetailRepo.findFoodDetailByfood(fUid);
		System.out.println("service de: " + de);
		
		return foodDetailRepo.findFoodDetailByfood(fUid);
	}
	
//	@Override
//	public List<Object[]> getFoodAndDetail(Food food) {
//		int fuid = food.getFuid();
//		return foodDetailRepo.findFoodAndFoodDetailByfuid(fuid);
//	}
}
