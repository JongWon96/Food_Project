package com.example.demo.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.FoodKcalDTO;
import com.example.demo.dto.UserBmrDto;
import com.example.demo.service.FoodDiaryService;

@SpringBootTest
public class FoodDiaryServiceTest {

    @Autowired
    private FoodDiaryService foodDiaryService;

    @Test
    @Disabled
    public void testCalculateBMR() {
        Long userUid = 1L; // 테스트 데이터의 사용자 UID
        UserBmrDto result = foodDiaryService.calculateBMR(userUid);

        assertNotNull(result);
        System.out.println("User BMR: " + result.getBmr());
    }

    @Test
    @Disabled
    public void testGetFoodUidByName() {
        String foodName = "apple";
        List<Long> result = foodDiaryService.getFoodUidByName(foodName);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        System.out.println("Food UIDs: " + result);
    }

    @Test
    @Disabled
    public void testGetFoodKcalByName() {
        String foodName = "apple";
        List<FoodKcalDTO> result = foodDiaryService.getFoodKcalByName(foodName);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(dto -> {
            System.out.println("Food: " + dto.getFoodName() + ", Calories: " + dto.getKcal());
        });
    }
}
