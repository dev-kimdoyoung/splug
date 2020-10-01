package com.project.splug.service;

import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import com.project.splug.domain.User;
import com.project.splug.domain.dto.CommentSaveRequestDTO;
import com.project.splug.repository.AccountRepository;
import com.project.splug.repository.CommentRepository;
import com.project.splug.repository.PostRepository;
import com.project.splug.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public List<Comment> findCommentByPost(Post post){
        return commentRepository.findAllByPost(post);
    }

    // 댓글 생성
    @Transactional
    public Long save(CommentSaveRequestDTO commentSaveRequestDTO){
        Post post = postRepository.findById(commentSaveRequestDTO.getPostIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + commentSaveRequestDTO.getPostIdx()));

        User user = userRepository.findById(new Long(1))
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        Comment comment = commentSaveRequestDTO.toEntity();
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment).getIdx();
    }

    // 댓글 수정
    @Transactional
    public Long update(Long idx, String content){
        Comment comment = commentRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        comment.update(content);

        return idx;
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long idx){
        Comment comment = commentRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + idx));

        commentRepository.delete(comment);
    }
}
