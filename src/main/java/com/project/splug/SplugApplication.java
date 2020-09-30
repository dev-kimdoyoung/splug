package com.project.splug;

import com.project.splug.domain.Post;
import com.project.splug.domain.User;
import com.project.splug.domain.enums.Department;
import com.project.splug.domain.enums.PostType;
import com.project.splug.domain.enums.RoleType;
import com.project.splug.repository.PostRepository;
import com.project.splug.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SplugApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplugApplication.class, args);
	}

	// 테스트용 더미 데이터 생성
	@Bean
	public CommandLineRunner runner(PostRepository postRepository, UserRepository userRepository){
		return args -> {

			// 테스트용 관리자 계정 생성
			User admin = userRepository.save(User.builder()
					.id("admin")
					.password("123")
					.name("관리자")
					.studentId("20150000")
					.email("test@test.com")
					.department(Department.computer)
					.dateOfBirth("960428")
					.phoneNumber("010-1234-5678")
					.password("{noop}123")
					.lastLoginTime(LocalDateTime.now())
					.roleType(RoleType.ADMIN)
					.build()
			);

			// 공지사항 게시글 생성
			Post notice = postRepository.save(Post.builder()
					.title("SPLUG 공지사항")
					.content("OO월 OO일 OO시 정모입니다.")
					.postType(PostType.notice)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(admin)
					.build()
			);

			// 자유게시판 게시글 생성
			Post free = postRepository.save(Post.builder()
					.title("반갑습니다.")
					.content("반갑습니다.")
					.postType(PostType.free)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(admin)
					.build()
			);
		};
	}
}
