package com.example.Food.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.Food.domain.FoodDiary;
import com.example.Food.dto.FoodCalendarDTO;
import com.example.Food.persistence.CalendarRepository;

@SpringBootTest
@Transactional
public class CalendarServiceTest {

    @Autowired
    private CalendarService foodCalendarService;

    @Autowired
    private CalendarRepository foodCalendarRepository;

    @Test
    @Disabled
    void testSaveFoodDiary() {
        // DTO 생성
        FoodCalendarDTO foodCalendarDTO = FoodCalendarDTO.builder()
            .date(LocalDate.of(2024, 12, 20)) // LocalDate 설정
            .foodId(1) // Food ID
            .userId(1) // User ID
            .dmeal(1)  // dmeal 설정
            .build();

        // 서비스 호출하여 데이터 저장
        FoodDiary savedDiary = foodCalendarService.saveFoodDiary(foodCalendarDTO);

        // 저장된 데이터 검증
        assertThat(savedDiary).isNotNull();
        assertThat(savedDiary.getDuid()).isNotNull(); // UID가 생성되었는지 확인
        assertThat(savedDiary.getDdate()).isEqualTo(LocalDate.of(2024, 12, 20)); // LocalDate로 확인
        assertThat(savedDiary.getFood().getFuid()).isEqualTo(1); // Food ID 확인
        assertThat(savedDiary.getUserInfo().getUuid()).isEqualTo(1); // User ID 확인
        assertThat(savedDiary.getDmeal()).isEqualTo(1); // dmeal 확인

        System.out.println("Saved FoodDiary: " + savedDiary);
    }

    @Test
    @Disabled
    void testFindByDdate() {
        // 테스트 데이터 준비
        FoodCalendarDTO foodCalendarDTO = FoodCalendarDTO.builder()
            .date(LocalDate.of(2024, 12, 20)) // LocalDate 설정
            .foodId(1)
            .userId(1)
            .dmeal(1)  // dmeal 설정
            .build();

        // 데이터 저장
        foodCalendarService.saveFoodDiary(foodCalendarDTO);

        // 특정 날짜로 데이터 조회
        var retrievedDiaries = foodCalendarRepository.findByDdate(LocalDate.of(2024, 12, 20));

        // 조회된 데이터 검증
        assertThat(retrievedDiaries).isNotEmpty();
        retrievedDiaries.forEach(fd -> {
            assertThat(fd.getDdate()).isEqualTo(LocalDate.of(2024, 12, 20)); // LocalDate로 비교
            assertThat(fd.getFood().getFuid()).isEqualTo(1);
            assertThat(fd.getUserInfo().getUuid()).isEqualTo(1);
            assertThat(fd.getDmeal()).isEqualTo(1); // dmeal 확인
            System.out.println("Retrieved FoodDiary: " + fd);
        });
    }
}
