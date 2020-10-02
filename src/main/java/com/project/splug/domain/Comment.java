package com.project.splug.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;


    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Builder
    public Comment(Post post, String content, User user){
        this.post = post;
        this.content = content;
        this.user = user;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public void update(String content){
        this.content = content;
        this.updatedDate = LocalDateTime.now();
    }
}
