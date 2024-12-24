package com.example.Food.service;

import com.example.Food.dto.FoodCalendarDTO;
import com.example.Food.dto.FoodKcalDTO;
import com.example.Food.persistence.FoodDiaryRepository;
import com.example.Food.persistence.FoodRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodDiaryService {
    List<FoodCalendarDTO> getFoodDiaryByDate(LocalDate date);

    void addFoodDiary(FoodCalendarDTO foodCalendarDTO);

    void deleteFoodDiary(Integer id);
    
    List<FoodCalendarDTO> getFoodDiaryByDateAndMeal(LocalDate date, int meal);
    
    int getTotalKcalByDate(LocalDate date);
}
