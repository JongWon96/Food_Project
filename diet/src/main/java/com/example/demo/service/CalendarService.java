package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.domain.FoodDiary;
import com.example.demo.dto.FoodCalendarDTO;

public interface CalendarService {

    /**
     * DTO를 사용하여 FoodDiary 엔티티를 저장합니다.
     * @param foodCalendarDTO 저장할 데이터 정보가 담긴 DTO
     * @return 저장된 FoodDiary 엔티티
     */
    FoodDiary saveFoodDiary(FoodCalendarDTO foodCalendarDTO);
    
    List<FoodCalendarDTO> getEventsInRange(LocalDate start, LocalDate end);
}