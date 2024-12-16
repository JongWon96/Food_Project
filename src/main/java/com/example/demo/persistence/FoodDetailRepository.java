package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.FoodDetail;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Integer>{

}
