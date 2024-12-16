package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Food;
import com.example.demo.dto.FoodKcalDTO;

@Repository
public interface FoodDiaryRepository extends JpaRepository<Food, Long> {

	// 사용자별 기초대사량 조회
	@Query("SELECT u.uName, u.uAge, u.uWeight, u.uHeight, u.uGender " +
		       "FROM UserInfo u WHERE u.uUid = :userUid")
		List<Object[]> findUserInfoByUid(@Param("userUid") Long userUid);

	// 음식 검색
	@Query("SELECT f.fUid FROM Food f WHERE f.fName LIKE %:foodName%")
	List<Long> findFoodUidByName(@Param("foodName") String foodName);

	// 선택한 음식의 칼로리
	@Query("SELECT new com.example.demo.dto.FoodKcalDTO(f.fName, fd.kcal) " + "FROM Food f JOIN f.foodDetail fd "
			+ "WHERE f.fName = :foodName")
	List<FoodKcalDTO> findFoodKcalByName(@Param("foodName") String foodName);
}