package com.example.Food.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Food.dto.UserBmrDto;

@SpringBootTest
class UserInfoRepositoryTest {

    @Autowired
    private UserRepository userInfoRepository;

    @Test
    @Disabled
    void testFindUserInfoByUid() {
        UserBmrDto bmr = userInfoRepository.findUserInfoByUid(1);
        assertThat(bmr).isNotNull();

        // DTO 값 출력
        System.out.println("DTO Result: " + bmr);
        System.out.println("Name: " + bmr.getName());
        System.out.println("Age: " + bmr.getAge());
        System.out.println("BMR: " + bmr.getBmr());

        assertThat(bmr.getName()).isEqualTo("John Doe");
        assertThat(bmr.getBmr()).isNotNull();
    }
}
