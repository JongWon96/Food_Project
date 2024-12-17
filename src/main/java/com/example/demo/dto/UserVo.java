package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    
    private String uId;    // 사용자 아이디
    private String uPw;    // 사용자 비밀번호
    private String uName;  // 사용자 이름
    private int phone;     // 사용자 전화번호
    private int uGender;   // 사용자 성별
    private int uAge;      // 사용자 나이
    private int uWeight;   // 사용자 체중
    private int uHeight;   // 사용자 키
    private int uGoal;     // 사용자 목표
    private int style;     // 스타일
    private String uAllergy;  // 사용자 알레르기


}
