package com.example.Food.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Food.domain.Food;
import com.example.Food.domain.FoodDiary;
import com.example.Food.domain.UserInfo;
import com.example.Food.dto.FoodCalendarDTO;
import com.example.Food.mapper.FoodDiaryMapper;
import com.example.Food.persistence.FoodDiaryRepository;
import com.example.Food.persistence.FoodRepository;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class FoodDiaryServiceImpl implements FoodDiaryService {

    private final FoodDiaryRepository foodDiaryRepository;
    private final FoodRepository foodRepository;
    private final HttpSession session;
    
    @Autowired
    public FoodDiaryServiceImpl(FoodDiaryRepository foodDiaryRepository,
                                FoodRepository foodRepository,
                                HttpSession session) {
        this.foodDiaryRepository = foodDiaryRepository;
        this.foodRepository = foodRepository;
        this.session = session;
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
        Food food = foodRepository.findById(foodCalendarDTO.getFoodId())
                                  .orElseThrow(() -> new IllegalArgumentException("음식을 찾을 수 없습니다."));

        // 세션에서 로그인된 사용자 가져오기
        UserInfo userInfo = (UserInfo) session.getAttribute("loginUser");
        if (userInfo == null) {
            throw new IllegalStateException("로그인된 사용자가 없습니다.");
        }

        FoodDiary foodDiary = new FoodDiary();
        foodDiary.setFood(food);
        foodDiary.setUserInfo(userInfo); // 세션에서 가져온 사용자 설정
        foodDiary.setDdate(foodCalendarDTO.getDate());
        foodDiary.setDmeal(foodCalendarDTO.getDmeal());
        foodDiaryRepository.save(foodDiary);
    }
    
    @Override
    public void deleteFoodDiary(Integer id) {
        foodDiaryRepository.deleteById(id); // ID 기준으로 삭제
    }
    
    @Override
    public List<FoodCalendarDTO> getFoodDiaryByDateAndMeal(LocalDate date, int meal) {
        List<FoodDiary> foodDiaries = foodDiaryRepository.findByDateAndMeal(date, meal);
        return foodDiaries.stream().map(foodDiary -> {
            FoodCalendarDTO dto = new FoodCalendarDTO();
            dto.setFoodId(foodDiary.getFood().getFuid());
            dto.setDate(foodDiary.getDdate());
            dto.setDmeal(foodDiary.getDmeal());
            dto.setFoodName(foodDiary.getFood().getFname());
            dto.setKcal(foodDiary.getFood().getFoodDetail().getKcal());
            dto.setCarb(foodDiary.getFood().getFoodDetail().getCarb());
            dto.setPrt(foodDiary.getFood().getFoodDetail().getPrt());
            dto.setFat(foodDiary.getFood().getFoodDetail().getFat());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public int getTotalKcalByDate(LocalDate date) {
        List<FoodDiary> foodDiaries = foodDiaryRepository.findByDate(date);
        return foodDiaries.stream()
            .mapToInt(fd -> fd.getFood().getFoodDetail().getKcal()) // null 체크 제거
            .sum();
    }
}
