package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.FoodDetail;

public interface FoodDetailRepository extends JpaRepository<FoodDetail, Integer>{
	@Query(value = "SELECT fd.* FROM food_detail fd "
            + "INNER JOIN food f ON fd.fuid = f.fuid "
            + "WHERE f.fuid = %?1%", nativeQuery = true)
//	@Query(value="SELECT fd FROM my_schema.FoodDetail fd "
//			+ " INNER JOIN my_schema.Food f ON fd.food.fuid = f.fuid"
//			+ + " WHERE fd.food.fuid = %?1% ", nativeQuery=true)
//	@Query(value="SELECT fd FROM FoodDetail fd JOIN fd.food f WHERE f.fuid=%?1%")
	public FoodDetail findFoodDetailByfood(int fUid);

}
