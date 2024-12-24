package com.example.Food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private String uid;    // 사용자 아이디
    private String upw;    // 사용자 비밀번호
    private String uname;  // 사용자 이름
    private int phone;     // 사용자 전화번호
    private int ugender;   // 사용자 성별
    private int uage;      // 사용자 나이
    private double uweight;   // 사용자 체중
    private double uheight;   // 사용자 키
    private int ugoal;     // 사용자 목표
    private int ustyle;     // 스타일
    private String uAllergy;  // 사용자 알레르기


}