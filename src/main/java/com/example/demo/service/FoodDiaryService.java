package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDiary;
import com.example.demo.dto.FoodKcalDTO;
import com.example.demo.dto.UserBmrDto;

public interface FoodDiaryService {
    UserBmrDto calculateBMR(Long userUid); // 사용자 BMR 계산
    
    List<Long> getFoodUidByName(String foodName); // 음식 UID 검색
    
    List<FoodKcalDTO> getFoodKcalByName(String foodName); // 음식 칼로리 조회
    
    FoodDiary saveFoodToDiary(Food food, Long userId, int mealType);
}