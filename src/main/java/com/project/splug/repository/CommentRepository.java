package com.project.splug.repository;

import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    void deleteAllByPost(Post post);
}
