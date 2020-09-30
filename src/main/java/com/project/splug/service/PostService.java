package com.project.splug.service;

import com.project.splug.domain.Post;
import com.project.splug.domain.User;
import com.project.splug.domain.enums.PostType;
import com.project.splug.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<Post> findPostList(Pageable pageable, PostType postType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return postRepository.findAllByPostType(pageable, postType);
    }

    public Post findPostByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }

    // 게시글 저장
    @Transactional
    public Long save(Post board) {
        return postRepository.save(board).getIdx();
    }

    // 게시글 수정
    @Transactional
    public Long update(Long idx, Post requestDto) {
        Post board = postRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPostType());

        return idx;
    }

    // 게시글 삭제
    @Transactional
    public void delete (Long idx) {
        Post board = postRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        postRepository.delete(board);
    }
}
