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
import com.example.demo.persistence.FoodDiaryRepository;

@Service
@Transactional
public class FoodDiaryServiceImpl implements FoodDiaryService {

    private final FoodDiaryRepository foodDiaryRepository;

    @Autowired
    public FoodDiaryServiceImpl(FoodDiaryRepository foodDiaryRepository) {
        this.foodDiaryRepository = foodDiaryRepository;
    }

    @Override
    public List<FoodCalendarDTO> getFoodDiaryByDate(LocalDate date) {
        return foodDiaryRepository.findByDate(date).stream()
                .map(foodDiary -> {
                    // null이면 기본값 0 설정
                    if (foodDiary.getDmeal() == null) {
                        foodDiary.setDmeal(0);
                    }
                    return FoodDiaryMapper.toDto(foodDiary);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addFoodDiary(FoodCalendarDTO foodCalendarDTO) {
        FoodDiary foodDiary = FoodDiaryMapper.toEntity(foodCalendarDTO);
        foodDiaryRepository.save(foodDiary); // FoodDiary 엔티티 저장
    }

    @Override
    public void deleteFoodDiary(Integer id) {
        foodDiaryRepository.deleteById(id); // ID 기준으로 삭제
    }
}
