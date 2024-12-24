package com.example.Food.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Food.domain.Food;
import com.example.Food.dto.FoodKcalDTO;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    // FoodKcalDTO로 음식 이름과 칼로리를 가져오는 메서드
    @Query("SELECT new com.example.Food.dto.FoodKcalDTO(f.fname, fd.kcal) " +
           "FROM Food f JOIN f.foodDetail fd WHERE f.fname = :name")
    FoodKcalDTO findFoodKcalByName(@Param("name") String name);

    // Food 엔티티로 음식 정보를 가져오는 메서드
    @Query("SELECT f FROM Food f WHERE f.fname = :name")
    Food findFoodByName(@Param("name") String name);
}