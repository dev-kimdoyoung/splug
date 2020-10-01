package com.project.splug.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Account {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String date;

    @Column
    private String usePlace;

    @Column
    private String useAmount;

    @Column
    private String remainAmount;

    @Column
    private String other;

    @Builder
    public Account(String date, String usePlace, String useAmount, String remainAmount, String other){
        this.date = date;
        this.usePlace = usePlace;
        this.useAmount = useAmount;
        this.remainAmount = remainAmount;
        this.other = other;
    }

    public void setRemainAmount(String remainAmount){
        this.remainAmount = remainAmount;
    }
}
