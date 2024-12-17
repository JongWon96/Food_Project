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


}