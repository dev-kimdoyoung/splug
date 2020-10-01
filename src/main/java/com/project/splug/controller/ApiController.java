package com.project.splug.controller;

import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import com.project.splug.domain.dto.AccountSaveRequestDTO;
import com.project.splug.domain.dto.CommentSaveRequestDTO;
import com.project.splug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PostService postService;

    // 게시글 저장
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody Post post) {
        return postService.save(post);
    }

    // 게시글 수정
    @PutMapping("/api/v1/posts/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody Post post) {
        return postService.update(idx, post);
    }

    // 게시글 삭제
    @DeleteMapping("/api/v1/posts/{idx}")
    public Long delete(@PathVariable Long idx) {
        postService.delete(idx);
        return idx;
    }

    // 댓글 생성
    @PostMapping("/api/v1/comment")
    public Long saveComement(@RequestBody CommentSaveRequestDTO commentSaveRequestDTO){
        return postService.saveComment(commentSaveRequestDTO);
    }

    // 댓글 수정
    @PutMapping("/api/v1/comment/{idx}")
    public Long updateComment(@PathVariable Long idx, @RequestBody String comment){
        return postService.updateComment(idx, comment);
    }

    // 댓글 삭제
    @DeleteMapping("/api/v1/comment/{idx}")
    public Long deleteDelete(@PathVariable Long idx){
        postService.deleteComment(idx);
        return idx;
    }

    // 회계 내역 생성
    @PostMapping("/api/v1/account")
    public Long saveAccount(@RequestBody AccountSaveRequestDTO accountSaveRequestDTO){
        return postService.saveAccount(accountSaveRequestDTO);
    }
}
