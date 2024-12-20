package com.example.demo.service;

import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.dto.FoodKcalDTO;

import java.time.LocalDate;
import java.util.List;

public interface FoodDiaryService {
    List<FoodCalendarDTO> getFoodDiaryByDate(LocalDate date);

    void addFoodDiary(FoodCalendarDTO foodCalendarDTO);

    void deleteFoodDiary(Integer id);
}
