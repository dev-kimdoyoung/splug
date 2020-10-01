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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;

    public Page<Post> findPostList(Pageable pageable, PostType postType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("idx").descending());

        return postRepository.findAllByPostType(pageable, postType);
    }

    public Post findPostByIdx(Long idx) {
        return postRepository.findById(idx).orElse(new Post());
    }

    public List<Comment> findCommentByPost(Post post){
        return commentRepository.findAllByPost(post);
    }

    public List<Account> findAllAccount(){
        return accountRepository.findAllByOrderByIdxDesc();
    }

    // 게시글 저장
    @Transactional
    public Long save(Post post) {
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

        postRepository.delete(post);
    }

    // 댓글 생성
    @Transactional
    public Long saveComment(CommentSaveRequestDTO commentSaveRequestDTO){
        Post post = postRepository.findById(commentSaveRequestDTO.getPostIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + commentSaveRequestDTO.getPostIdx()));

        Long id = new Long(1);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Comment comment = commentSaveRequestDTO.toEntity();
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment).getIdx();
    }

    // 댓글 수정
    @Transactional
    public Long updateComment(Long idx, String content){
        Comment comment = commentRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        comment.update(content);

        return idx;
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long idx){
        Comment comment = commentRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        commentRepository.delete(comment);
    }

    // 회계 내역 생성
    @Transactional
    public Long saveAccount(AccountSaveRequestDTO accountsaveRequestDTO){
        Account account = accountRepository.findFirstByOrderByIdxDesc();
        int remainAmount = Integer.parseInt(account.getRemainAmount()) + Integer.parseInt(accountsaveRequestDTO.getUseAmount());

        account = accountsaveRequestDTO.toEntity();
        account.setRemainAmount(Integer.toString(remainAmount));

        return accountRepository.save(account).getIdx();
    }
}
