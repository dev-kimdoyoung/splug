package com.project.splug.controller;

import com.project.splug.domain.enums.PostType;
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

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/notice")
    public String notice(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.notice));
        return "notice";
    }

    @GetMapping("/free")
    public String free(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("postList", postService.findPostList(pageable, PostType.free));
        return "free";
    }

    @GetMapping("/read")
    public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model){
        model.addAttribute("post", postService.findPostByIdx(idx));
        return "readform";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    @GetMapping("/post")
    public String post(){
        return "postform";
    }
}
