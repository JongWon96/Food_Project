package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Food;
import com.example.demo.dto.FoodKcalDTO;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    @Disabled
    public void testGetFoodKcalByName() {
        // Given
        String foodName = "apple";
        System.out.println("Testing getFoodKcalByName with name: " + foodName);

        // When
        FoodKcalDTO result = foodService.getFoodKcalByName(foodName);

        // Then
        System.out.println("Result from getFoodKcalByName: " + result);
        assertThat(result).isNotNull();
        assertThat(result.getFoodName()).isEqualTo(foodName);
        assertThat(result.getKcal()).isGreaterThan(0);
    }

    @Test
    @Disabled
    public void testGetFoodByName() {
        // Given
        String foodName = "apple";
        System.out.println("Testing getFoodByName with name: " + foodName);

        // When
        Food result = foodService.getFoodByName(foodName);

        // Then
        System.out.println("Result from getFoodByName: " + result);
        assertThat(result).isNotNull();
        assertThat(result.getFname()).isEqualTo(foodName);
        assertThat(result.getFuid()).isNotNull();
    }
}
