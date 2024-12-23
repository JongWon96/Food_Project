package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.FoodDiary;
import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.mapper.FoodDiaryMapper;
import com.example.demo.persistence.CalendarRepository;

@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarServiceImpl(CalendarRepository foodCalendarRepository) {
        this.calendarRepository = foodCalendarRepository;
    }

    @Override
    public FoodDiary saveFoodDiary(FoodCalendarDTO foodCalendarDTO) {
        FoodDiary foodDiary = FoodDiaryMapper.toEntity(foodCalendarDTO);
        return calendarRepository.save(foodDiary);
    }
    
    @Override
    public List<FoodCalendarDTO> getEventsInRange(LocalDate start, LocalDate end) {
        return calendarRepository.findByDateRange(start, end).stream()
                .map(FoodDiaryMapper::toDto) // Repository에서 가져온 데이터를 DTO로 변환
                .collect(Collectors.toList());
    }
}
