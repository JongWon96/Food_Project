package com.example.Food.service;

import com.example.Food.domain.Food;
import com.example.Food.dto.FoodKcalDTO;
import com.example.Food.persistence.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // 음식 이름으로 칼로리 정보를 가져오는 메서드 구현
    @Override
    public FoodKcalDTO getFoodKcalByName(String name) {
        return foodRepository.findFoodKcalByName(name);
    }

    // 음식 이름으로 Food 엔티티 정보를 가져오는 메서드 구현
    @Override
    public Food getFoodByName(String name) {
        return foodRepository.findFoodByName(name);
    }
}
