package com.project.splug.domain.dto;

import com.project.splug.domain.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleChangeDTO {

    private Long idx;
    private RoleType roleType;

    @Builder
    public RoleChangeDTO(Long idx, RoleType roleType) {
        this.idx = idx;
        this.roleType = roleType;
    }
}

