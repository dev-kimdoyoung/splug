package com.project.splug.controller;

import com.project.splug.domain.Post;
import com.project.splug.domain.enums.PostType;
import com.project.splug.service.AccountService;
import com.project.splug.service.CommentService;
import com.project.splug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final PostService postService;
    private final CommentService commentService;
    private final AccountService accountService;

    // 메인페이지
    @GetMapping("/")
    public String main(){
        return "main";
    }

    // 공지사항
    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.notice));
        return "notice";
    }

    // 자유게시판
    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.free));
        return "free";
    }

    // 게시글 쓰기 폼
    @GetMapping("/post")
    public String post(){
        return "postwrite";
    }

    // 게시글 읽기 폼
    @GetMapping("/read")
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model){
        Post post = postService.findPostByIdx(idx);
        model.addAttribute("post", post);
        model.addAttribute("commentList", commentService.findCommentByPost(post));
        return "postread";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // 회원가입 폼
    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    // 회계
    @GetMapping("/account")
    public String account(Model model){
        model.addAttribute("accountList", accountService.findAllAccount());
        return "accounting";
    }

    @GetMapping("/addAccount")
    public String addAccount(){
        return "addAccount";
    }
}
