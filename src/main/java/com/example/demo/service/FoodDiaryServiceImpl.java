package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDiary;
import com.example.demo.domain.UserInfo;
import com.example.demo.dto.FoodKcalDTO;
import com.example.demo.dto.UserBmrDto;
import com.example.demo.persistence.FoodDiaryRepository;

@Service
public class FoodDiaryServiceImpl implements FoodDiaryService {

    private final FoodDiaryRepository foodDiaryRepository;

    public FoodDiaryServiceImpl(FoodDiaryRepository foodDiaryRepository) {
        this.foodDiaryRepository = foodDiaryRepository;
    }

    @Override
    public UserBmrDto calculateBMR(Long userUid) {
        // 사용자 정보를 조회
        List<Object[]> userInfo = foodDiaryRepository.findUserInfoByUid(userUid);

        if (userInfo.isEmpty()) {
            throw new IllegalArgumentException("User not found with UID: " + userUid);
        }

        Object[] user = userInfo.get(0);
        String name = (String) user[0];
        Integer age = (Integer) user[1];
        Double weight = ((Number) user[2]).doubleValue();
        Double height = ((Number) user[3]).doubleValue();
        Integer gender = (Integer) user[4];

        // BMR 계산
        Double bmr = null;
        if (gender == 1) { // 남자
            bmr = 66.47 + (13.75 * weight) + (5 * height) - (6.76 * age);
        } else if (gender == 2) { // 여자
            bmr = 665.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age);
        }

        return new UserBmrDto(name, age, weight, height, gender, bmr);
    }

    @Override
    public List<Long> getFoodUidByName(String foodName) {
        return foodDiaryRepository.findFoodUidByName(foodName);
    }

    @Override
    public List<FoodKcalDTO> getFoodKcalByName(String foodName) {
        return foodDiaryRepository.findFoodKcalByName(foodName);
    }
    
    @Override
    public FoodDiary saveFoodToDiary(Food food, Long userId, int mealType) {
        // 사용자 정보 가져오기
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // FoodDiary 객체 생성 및 저장
        FoodDiary foodDiary = new FoodDiary();
        foodDiary.setFood(food);
        foodDiary.setUserInfo(userInfo);
        foodDiary.setD_date(new Date());
        foodDiary.setD_meal(mealType);

        return foodDiaryRepository.save(foodDiary);
    }
}