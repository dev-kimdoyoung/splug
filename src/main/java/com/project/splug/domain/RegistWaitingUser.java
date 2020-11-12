package com.project.splug.domain;

import com.project.splug.domain.enums.Department;
import com.project.splug.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class RegistWaitingUser implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String studentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column
    private String dateOfBirth;

    @Column
    private String phoneNumber;

    @Column
    private LocalDateTime registRequestTime;

    @Builder
    public RegistWaitingUser(String id, String name, String studentId, Department department, String dateOfBirth, String phoneNumber, String password){
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.registRequestTime = LocalDateTime.now();
    }
}