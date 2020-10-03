package com.project.splug.domain;

import com.project.splug.domain.enums.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
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

    @Column(length = 30000)
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

    @Column
    private int views;

    @Column
    private String thumbnail;

    @Column
    private int commentCount;

    @Builder
    public Post(String title, String content, PostType postType, User user, int views, String thumbnail, int commentCount){
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.user = user;
        this.views = views;
        this.thumbnail = thumbnail;
        this.commentCount = commentCount;
    }

    // 게시글 Update
    public void update(String title, String content, PostType postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.updatedDate = LocalDateTime.now();
    }

    // 조회수 Update
    public void updatePostViews(){
        this.views = views + 1;
    }

    // 댓글 수 Update
    public void updateCommentCount(){
        this.commentCount = commentCount + 1;
    }
}
