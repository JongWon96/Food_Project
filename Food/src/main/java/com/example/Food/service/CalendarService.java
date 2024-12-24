package com.example.Food.service;

import java.time.LocalDate;
import java.util.List;

import com.example.Food.domain.FoodDiary;
import com.example.Food.dto.FoodCalendarDTO;

public interface CalendarService {

    /**
     * DTO를 사용하여 FoodDiary 엔티티를 저장합니다.
     * @param foodCalendarDTO 저장할 데이터 정보가 담긴 DTO
     * @return 저장된 FoodDiary 엔티티
     */
    FoodDiary saveFoodDiary(FoodCalendarDTO foodCalendarDTO);
    
    List<FoodCalendarDTO> getEventsInRange(LocalDate start, LocalDate end);
}