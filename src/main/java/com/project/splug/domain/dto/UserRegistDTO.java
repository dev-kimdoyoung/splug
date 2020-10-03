package com.project.splug.domain.dto;

import com.project.splug.domain.enums.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegistDTO {

    private String id;
    private String password;
    private String name;
    private String studentId;
    private Department department;
    private String dateOfBirth;
    private String phoneNumber;

    @Builder
    public UserRegistDTO(String id, String password, String name, String studentId, Department department, String dateOfBirth, String phoneNumber){
        this.id = id;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
