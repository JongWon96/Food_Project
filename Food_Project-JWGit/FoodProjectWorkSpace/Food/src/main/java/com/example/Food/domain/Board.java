package com.example.Food.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "B_UID")  
    private int buid;  

    @Column(name = "B_TITLE") 
    private String btitle;  

    @Column(name = "B_CONTENT") 
    private String bcontent; 

    @Column(name = "B_CREATEAT") 
    @ColumnDefault("sysdate")
    private Date bcreateAt; 

    @Column(name = "B_CNT")
    private int bcnt;  

    @Column(name = "B_IMG", nullable = true)
    private String bimg;

    @Column(name = "B_DANGER") 
    @ColumnDefault("0")
    private int bdanger; 
    
    @ManyToOne
    @JoinColumn(name = "U_UID")
    private UserInfo author; 

   
    
    //보드삭제시 해당 댓글 자동삭제코드
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> comments = new ArrayList<>();

    @Column(name = "B_likecnt")
    private int blikecnt;
}
