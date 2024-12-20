package com.example.demo.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.FoodDiary;

@Repository
public interface CalendarRepository extends JpaRepository<FoodDiary, Integer> {
    // 특정 날짜에 해당하는 FoodDiary 조회
	@Query("SELECT fd FROM FoodDiary fd JOIN FETCH fd.food JOIN FETCH fd.userInfo WHERE fd.ddate = :ddate")
	List<FoodDiary> findByDdate(@Param("ddate") LocalDate ddate);
	
	@Query("SELECT fd FROM FoodDiary fd WHERE fd.ddate BETWEEN :start AND :end")
	List<FoodDiary> findByDateRange(@Param("start") LocalDate start, @Param("end") LocalDate end);
}