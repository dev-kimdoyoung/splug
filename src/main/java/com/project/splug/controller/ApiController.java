package com.project.splug.controller;

import com.project.splug.domain.Post;
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
}
