package com.example.Food.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.Food.domain.Food;
import com.example.Food.domain.FoodDiary;
import com.example.Food.domain.UserInfo;

@SpringBootTest
@Transactional
public class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository foodCalendarRepository;

    @Test
    @Disabled
    void testSaveAndFindByDdate() {
        // Food와 UserInfo 객체 생성
        Food food = Food.builder()
            .fuid(1) // 기존에 DB에 존재하는 Food ID
            .build();

        UserInfo user = UserInfo.builder()
            .uuid(1) // 기존에 DB에 존재하는 User ID
            .build();

        // FoodDiary 엔티티 생성 및 저장
        FoodDiary foodDiary = FoodDiary.builder()
            .ddate(LocalDate.of(2024, 12, 19)) // LocalDate로 날짜 설정
            .dmeal(1) // 식사 정보 설정
            .food(food)  // Food 설정
            .userInfo(user)  // UserInfo 설정
            .build();

        foodCalendarRepository.save(foodDiary);

        // 특정 날짜로 데이터 조회
        List<FoodDiary> foodDiaries = foodCalendarRepository.findByDdate(LocalDate.of(2024, 12, 19)); // LocalDate 사용

        // 조회된 데이터 검증
        assertThat(foodDiaries).isNotEmpty(); // 결과가 비어있지 않은지 확인
        foodDiaries.forEach(fd -> {
            assertThat(fd.getDdate()).isEqualTo(LocalDate.of(2024, 12, 19)); // 날짜 확인
            assertThat(fd.getFood().getFuid()).isEqualTo(1); // Food ID 확인
            assertThat(fd.getUserInfo().getUuid()).isEqualTo(1); // User ID 확인
            assertThat(fd.getDmeal()).isEqualTo(1); // 식사 정보 확인
            System.out.println("FoodDiary: " + fd);
        });
    }
}
