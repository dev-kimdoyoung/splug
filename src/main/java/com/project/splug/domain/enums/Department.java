package com.project.splug.domain.enums;

public enum Department {
    computer("컴퓨터학부"),
    software("소프트웨어학부");

    private String value;

    Department(String value){
        this.value = value;
    }
}
