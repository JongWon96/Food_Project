package com.example.Food.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Food.domain.Food;
import com.example.Food.dto.FoodKcalDTO;

@SpringBootTest
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    @Disabled
    public void testFindFoodKcalByName() {
        // Given
        String foodName = "apple";
        System.out.println("Testing findFoodKcalByName with name: " + foodName);

        // When
        FoodKcalDTO result = foodRepository.findFoodKcalByName(foodName);

        // Then
        System.out.println("Result from findFoodKcalByName: " + result);
        assertThat(result).isNotNull();
        assertThat(result.getFoodName()).isEqualTo(foodName);
        assertThat(result.getKcal()).isGreaterThan(0);
    }

    @Test
    @Disabled
    public void testFindFoodByName() {
        // Given
        String foodName = "apple";
        System.out.println("Testing findFoodByName with name: " + foodName);

        // When
        Food result = foodRepository.findFoodByName(foodName);

        // Then
        System.out.println("Result from findFoodByName: " + result);
        assertThat(result).isNotNull();
        assertThat(result.getFname()).isEqualTo(foodName);
        assertThat(result.getFuid()).isNotNull();
    }
}
