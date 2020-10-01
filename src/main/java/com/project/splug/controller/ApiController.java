package com.project.splug.controller;

import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import com.project.splug.domain.dto.AccountSaveRequestDTO;
import com.project.splug.domain.dto.CommentSaveRequestDTO;
import com.project.splug.service.AccountService;
import com.project.splug.service.CommentService;
import com.project.splug.service.ImageUploadService;
import com.project.splug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PostService postService;
    private final CommentService commentService;
    private final AccountService accountService;
    private final ImageUploadService imageUploadService;

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
        return commentService.save(commentSaveRequestDTO);
    }

    // 댓글 수정
    @PutMapping("/api/v1/comment/{idx}")
    public Long updateComment(@PathVariable Long idx, @RequestBody String comment){
        return commentService.update(idx, comment);
    }

    // 댓글 삭제
    @DeleteMapping("/api/v1/comment/{idx}")
    public Long deleteDelete(@PathVariable Long idx){
        commentService.delete(idx);
        return idx;
    }

    // 회계 내역 생성
    @PostMapping("/api/v1/account")
    public Long saveAccount(@RequestBody AccountSaveRequestDTO accountSaveRequestDTO){
        return accountService.save(accountSaveRequestDTO);
    }

    // ckeditor 이미지 업로드
    @PostMapping("/api/v1/imageUpload")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload){
        imageUploadService.imageUpload(request, response, multiFile, upload);
    }

    @GetMapping("/api/v1/imageChk")
    public void imageCheck(@RequestParam(value="uid") String uid, @RequestParam(value="fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException{
        imageUploadService.ckSubmit(uid, fileName, request, response);
    }
}
