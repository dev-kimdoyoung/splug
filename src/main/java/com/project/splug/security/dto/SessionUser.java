package com.project.splug.security.dto;

import com.project.splug.domain.User;
import com.project.splug.domain.enums.Department;
import com.project.splug.domain.enums.RoleType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String id;
    private String name;
    private Department department;
    private RoleType roleType;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.department = user.getDepartment();
        this.roleType = user.getRoleType();
    }
}