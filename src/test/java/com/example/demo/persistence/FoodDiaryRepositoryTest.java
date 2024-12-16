package com.example.demo.persistence;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.FoodKcalDTO;

@SpringBootTest
public class FoodDiaryRepositoryTest {

    @Autowired
    private FoodDiaryRepository foodDiaryRepository;

    @Test
    @Disabled
    public void testFindUserInfoByUid() {
        Long userUid = 1L; // 테스트할 사용자 UID

        // When: 사용자 정보를 조회
        List<Object[]> result = foodDiaryRepository.findUserInfoByUid(userUid);

        // Then: 결과 출력
        for (Object[] user : result) {
            String name = (String) user[0];
            Integer age = (Integer) user[1];
            Double weight = ((Number) user[2]).doubleValue();
            Double height = ((Number) user[3]).doubleValue();
            Integer gender = (Integer) user[4];

            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Weight: " + weight);
            System.out.println("Height: " + height);
            System.out.println("Gender: " + gender);
        }
    }

    @Test
    @Disabled
    public void testFindFoodUidByName() {
        String foodName = "apple"; // 테스트할 음식 이름

        // When: 음식 UID 조회
        List<Long> foodUids = foodDiaryRepository.findFoodUidByName(foodName);

        // Then: 결과 출력
        System.out.println("Food UIDs: " + foodUids);
    }

    @Test
    @Disabled
    public void testFindFoodKcalByName() {
        String foodName = "apple"; // 테스트할 음식 이름

        // When: 음식 칼로리 조회
        List<FoodKcalDTO> result = foodDiaryRepository.findFoodKcalByName(foodName);

        // Then: 결과 출력
        for (FoodKcalDTO dto : result) {
            System.out.println("음식명: " + dto.getFoodName());
            System.out.println("칼로리: " + dto.getKcal());
        }
    }
}