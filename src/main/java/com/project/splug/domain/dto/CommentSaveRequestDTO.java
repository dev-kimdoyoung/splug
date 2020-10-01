package com.project.splug.domain.dto;

import com.project.splug.domain.Comment;
import com.project.splug.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDTO {

    private Long postIdx;
    private String content;

    @Builder
    public CommentSaveRequestDTO(Long postIdx, String content){
        this.postIdx = postIdx;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
