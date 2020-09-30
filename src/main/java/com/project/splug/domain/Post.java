package com.project.splug.domain;

import com.project.splug.domain.enums.PostType;
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
public class Post implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Post(String title, String content, PostType postType, LocalDateTime createdDate, LocalDateTime updatedDate, User user){
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }

    public void update(String title, String content, PostType postType) {    // 게시글 업데이트 메소드
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.updatedDate = LocalDateTime.now();
    }
}
