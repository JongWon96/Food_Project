package com.example.Food.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Food.domain.Food;
import com.example.Food.domain.FoodDiary;
import com.example.Food.dto.FoodKcalDTO;

@Repository
public interface FoodDiaryRepository extends JpaRepository<FoodDiary, Integer> {

    // 특정 날짜의 FoodDiary 조회
    @Query("SELECT fd FROM FoodDiary fd WHERE fd.ddate = :date")
    List<FoodDiary> findByDate(@Param("date") LocalDate date);
    
    @Query("SELECT fd FROM FoodDiary fd WHERE fd.ddate = :date AND fd.dmeal = :meal")
    List<FoodDiary> findByDateAndMeal(@Param("date") LocalDate date, @Param("meal") int meal);
}