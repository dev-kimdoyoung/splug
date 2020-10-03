package com.project.splug.controller;

import com.project.splug.domain.Post;
import com.project.splug.domain.enums.PostType;
import com.project.splug.security.dto.SessionUser;
import com.project.splug.security.annotation.LoginUser;
import com.project.splug.service.AccountService;
import com.project.splug.service.CommentService;
import com.project.splug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
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
    public String main(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "main";
    }

    // 공지사항
    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.notice));
        model.addAttribute("sessionUser", user);
        return "notice";
    }

    // 자유게시판
    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.free));
        model.addAttribute("sessionUser", user);
        return "free";
    }

    // 게시글 쓰기 폼
    @GetMapping("/post")
    public String post(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "postwrite";
    }

    // 게시글 읽기 폼
    @GetMapping("/read")
    public String read(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        Post post = postService.findPostByIdx(idx);
        postService.updatePostViews(post);
        model.addAttribute("post", post);
        model.addAttribute("sessionUser", user);
        model.addAttribute("commentList", commentService.findCommentByPost(post));
        return "postread";
    }

    // 게시글 수정 폼
    @GetMapping("/postUpdate")
    public String postModify(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model, @LoginUser SessionUser user){
        model.addAttribute("post", postService.findPostByIdx(idx));
        model.addAttribute("sessionUser", user);
        return "postwrite";
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

    // 활동 게시판
    @GetMapping("/activity")
    public String activity(Model model, @LoginUser SessionUser user){
        model.addAttribute("postList", postService.findActivityPost());
        return "activity";
    }

    // 회계
    @GetMapping("/account")
    public String account(Model model, @LoginUser SessionUser user){
        model.addAttribute("accountList", accountService.findAllAccount());
        model.addAttribute("sessionUser", user);
        return "accounting";
    }

    // 회계 내역 추가 폼
    @GetMapping("/addAccount")
    public String addAccount(Model model, @LoginUser SessionUser user){
        model.addAttribute("sessionUser", user);
        return "addAccount";
    }
}
