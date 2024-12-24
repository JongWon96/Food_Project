package com.example.Food.service;

import com.example.Food.domain.Food;
import com.example.Food.dto.FoodKcalDTO;

public interface FoodService {
    // 음식 이름으로 칼로리 정보를 가져오는 메서드
    FoodKcalDTO getFoodKcalByName(String name);

    // 음식 이름으로 Food 엔티티 정보를 가져오는 메서드
    Food getFoodByName(String name);
}
