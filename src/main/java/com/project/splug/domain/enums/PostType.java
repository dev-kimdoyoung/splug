package com.project.splug.domain.enums;

public enum PostType {
    notice("공지사항"),
    free("자유게시판");

    private String value;

    PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
