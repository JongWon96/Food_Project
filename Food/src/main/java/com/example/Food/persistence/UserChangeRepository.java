package com.example.Food.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import com.example.Food.domain.UserChange;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChangeRepository extends JpaRepository<UserChange, Integer> {

    // User_Info 테이블에서 변경된 체중과 키를 User_Change 테이블에 백업
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User_Change (U_Weight, U_Height, U_Uid) VALUES(:uweight, :uheight, :uuid)", nativeQuery = true)
    void backupUserChange(@Param("uuid") int uuid,
                          @Param("uweight") double uweight,
                          @Param("uheight") double uheight);

    // User_Info 테이블의 체중과 키를 업데이트
    @Transactional
    @Modifying
    @Query(value = "UPDATE User_Info SET U_Weight = :uweight, U_Height = :uheight, " +
            "U_Style = :style, U_Allergy = :uallergy WHERE U_Uid = :uuid", nativeQuery = true)
    void updateUserChange(@Param("uuid") int uuid,
                          @Param("uweight") double uweight,
                          @Param("uheight") double uheight,
                          @Param("style") int style,
                          @Param("uallergy") String uallergy);

    // 체중과 키로 UserChange 검색
    List<UserChange> findByUcweightAndUcheight(double uweight, double uheight);

    // ID로 UserChange 검색
    Optional<UserChange> findById(int ucid);
}