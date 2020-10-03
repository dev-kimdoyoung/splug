# 스플럭 웹페이지

## 구현 내용

  - 공지사항, 자유게시판, 활동게시판 구현(CRUD)
  - CKeditor4로 글 쓰기 페이지 구현 및 이미지 업로드 구현
  - 댓글 생성 및 조회 구현(update, delete는 프론트 미구현)
  - 회계 내역 생성 및 조회 구현(update, delete는 프톤트 및 백엔드 미구현)
  - 게시글 조회수 기능 추가
  - SpringSecurity 적용
  - 로그인 및 회원가입 구현
  - 활동게시판 썸네일 구현
  
## 구현해야 할 내용

  - 댓글 CRUD(프론트)
  - 로그인 프론트
  - 체크된 공지사항 상위 노출 기능
  - 댓글 수 표시
  - 좋아요 싫어요 표시
  - 그림이 있으면 그림있는 표시
  - 게시글 검색기능
  - 관리자 페이지(유저 권한 설정)
  
## 사용법

- config 폴더의 MvcConfiguration.java 이미지 Resource 경로 수정
```git
addResourceLocations("file:///{이미지 Resource 폴더 경로}")

ex) addResourceLocations("file:////Users/ggomak/Desktop/SplugServerImg/")
```

- service 폴더의 ImageUploadService.java 이미지 업로드 경로 수정
```git
private String fileDir = "{이미지 업로드 경로}";

ex) private String fileDir = "/Users/ggomak/Desktop/SplugServerImg/post/";
```
  
## QA
  
  - addAccount.html input class 정수형으로 변경
  
## 참조

  - CKEditor4
  - ImageUploadService : https://mine-it-record.tistory.com/
  - Template : https://unsplash.com/