package com.example.Food.mapper;

import com.example.Food.domain.Food;
import com.example.Food.domain.FoodDiary;
import com.example.Food.domain.UserInfo;
import com.example.Food.dto.FoodCalendarDTO;

public class FoodDiaryMapper {

    public static FoodDiary toEntity(FoodCalendarDTO dto) {
        Food food = Food.builder()
                .fuid(dto.getFoodId())
                .build();
        UserInfo user = UserInfo.builder()
                .uuid(dto.getUserId())
                .build();

        return FoodDiary.builder()
                .ddate(dto.getDate())
                .dmeal(dto.getDmeal())
                .food(food)
                .userInfo(user)
                .build();
    }

    public static FoodCalendarDTO toDto(FoodDiary entity) {
        return FoodCalendarDTO.builder()
                .date(entity.getDdate())
                .foodId(entity.getFood().getFuid())
                .userId(entity.getUserInfo().getUuid())
                .dmeal(entity.getDmeal())
                .build();
    }
}
