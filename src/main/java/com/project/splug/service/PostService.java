package com.project.splug.service;

import com.project.splug.domain.Account;
import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import com.project.splug.domain.User;
import com.project.splug.domain.dto.AccountSaveRequestDTO;
import com.project.splug.domain.dto.CommentSaveRequestDTO;
import com.project.splug.domain.enums.PostType;
import com.project.splug.repository.AccountRepository;
import com.project.splug.repository.CommentRepository;
import com.project.splug.repository.PostRepository;
import com.project.splug.repository.UserRepository;
import com.project.splug.security.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public Page<Post> findPostList(Pageable pageable, PostType postType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return postRepository.findAllByPostType(pageable, postType);
    }

    public Post findPostByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }

    public List<Post> findActivityPost(){
        return postRepository.findAllByPostTypeOrderByIdxDesc(PostType.activity);
    }

    public String findImgSrc(Post posts){

        try {
            String hashName = posts.getContent().substring(posts.getContent().indexOf("uid=") + 4, posts.getContent().indexOf("&amp;"));

            String fileName = posts.getContent().substring(posts.getContent().indexOf("&amp;fileName=") + 14);
            fileName = fileName.substring(0, fileName.indexOf("\""));

            return "images/post/" + hashName + "_" + fileName;
        }
        catch(Exception e){
            return "images/no_img.gif";
        }
    }

    // 게시글 저장
    @Transactional
    public Long save(Post post, SessionUser sessionUser) {
        User user = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + sessionUser.getId()));


        post.setUser(user);
        post.setCreatedDate(LocalDateTime.now());
        post.setUpdatedDate(LocalDateTime.now());
        post.setThumbnail(findImgSrc(post));

        return postRepository.save(post).getIdx();
    }

    // 게시글 수정
    @Transactional
    public Long update(Long idx, Post requestDto) {
        Post post = postRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPostType());

        return idx;
    }

    // 게시글 삭제
    @Transactional
    public void delete (Long idx) {
        Post post = postRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        commentRepository.deleteAllByPost(post);
        postRepository.delete(post);
    }

    // 조회수 업데이트
    @Transactional
    public void updatePostViews(Post post){
        post.updatePostViews();
    }
}
