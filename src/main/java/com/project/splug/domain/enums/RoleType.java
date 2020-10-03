package com.project.splug.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    GUEST("ROLE_GUEST", "손님"),
    PREMEMBER("ROLE_PREMEMBER", "준회원"),
    MEMBER("ROLE_MEMBER", "정회원"),
    GRADUATE("ROLE_GRADUATE", "졸업생"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
