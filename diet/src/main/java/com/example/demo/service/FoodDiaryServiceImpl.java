package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDiary;
import com.example.demo.dto.FoodCalendarDTO;
import com.example.demo.mapper.FoodDiaryMapper;
import com.example.demo.persistence.FoodDiaryRepository;
import com.example.demo.persistence.FoodRepository;

@Service
@Transactional
public class FoodDiaryServiceImpl implements FoodDiaryService {

    private final FoodDiaryRepository foodDiaryRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodDiaryServiceImpl(FoodDiaryRepository foodDiaryRepository, FoodRepository foodRepository) {
        this.foodDiaryRepository = foodDiaryRepository;
        this.foodRepository = foodRepository;
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

    public void addFoodDiary(FoodCalendarDTO foodCalendarDTO) {
        Food food = foodRepository.findById(foodCalendarDTO.getFoodId())
                                  .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다."));

        FoodDiary foodDiary = new FoodDiary();
        foodDiary.setFood(food);
        foodDiary.setDdate(foodCalendarDTO.getDate());
        foodDiary.setDmeal(foodCalendarDTO.getDmeal());
        foodDiaryRepository.save(foodDiary);
    }
    
    @Override
    public void deleteFoodDiary(Integer id) {
        foodDiaryRepository.deleteById(id); // ID 기준으로 삭제
    }
}
