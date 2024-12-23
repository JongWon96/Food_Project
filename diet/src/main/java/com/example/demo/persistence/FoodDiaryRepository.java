package com.example.demo.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Food;
import com.example.demo.domain.FoodDiary;
import com.example.demo.dto.FoodKcalDTO;

@Repository
public interface FoodDiaryRepository extends JpaRepository<FoodDiary, Integer> {

    // 특정 날짜의 FoodDiary 조회
    @Query("SELECT fd FROM FoodDiary fd WHERE fd.ddate = :date")
    List<FoodDiary> findByDate(@Param("date") LocalDate date);
}