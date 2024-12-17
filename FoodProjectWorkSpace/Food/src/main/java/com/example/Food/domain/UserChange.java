package com.example.Food.domain;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "User_Change")
public class UserChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UC_Uid", nullable = false)  // UC_Uid는 기본 키
    private int ucid;

    @ManyToOne
    @JoinColumn(name = "U_Uid", referencedColumnName = "U_Uid", nullable = false)  // 외래 키로 UserInfo의 U_Uid 참조
    private UserInfo userInfo;

    @ColumnDefault("sysdate")
    @Column(name = "UC_Date", nullable = false)
    private Date ucdate;

    @Column(name = "U_Height")
    private double ucheight;

    @Column(name = "U_Weight")
    private double ucweight;
}

