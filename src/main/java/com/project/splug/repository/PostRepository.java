package com.project.splug.repository;

import com.project.splug.domain.Post;
import com.project.splug.domain.enums.PostType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByPostType(Pageable pageable, PostType postType);

    List<Post> findAllByPostTypeOrderByIdxDesc(PostType postType);
}
