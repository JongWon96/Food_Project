package com.example.demo.service;

import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.dto.FoodKcalDTO;
import com.example.demo.persistence.FoodDiaryRepository;
import com.example.demo.persistence.FoodRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodDiaryService {
    List<FoodCalendarDTO> getFoodDiaryByDate(LocalDate date);

    void addFoodDiary(FoodCalendarDTO foodCalendarDTO);

    void deleteFoodDiary(Integer id);
}
