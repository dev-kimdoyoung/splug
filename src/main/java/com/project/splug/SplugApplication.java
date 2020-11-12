package com.project.splug;

import com.project.splug.domain.*;
import com.project.splug.domain.enums.Department;
import com.project.splug.domain.enums.PostType;
import com.project.splug.domain.enums.RoleType;
import com.project.splug.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class SplugApplication {

	BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		SpringApplication.run(SplugApplication.class, args);
	}

	// 테스트용 더미 데이터 생성
	@Bean
	public CommandLineRunner runner(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, AccountRepository accountRepository, RegistWaitingUserRepository registWaitingUserRepository){
		return args -> {

			registWaitingUserRepository.save(RegistWaitingUser.builder()
					.id("123")
					.password(bcryptPasswordEncoder.encode("123"))
					.name("test1")
					.studentId("12312312")
					.department(Department.computer)
					.dateOfBirth("000000")
					.phoneNumber("123-1234-1234")
					.build()
			);

			registWaitingUserRepository.save(RegistWaitingUser.builder()
					.id("321")
					.password(bcryptPasswordEncoder.encode("123"))
					.name("test2")
					.studentId("32132132")
					.department(Department.computer)
					.dateOfBirth("111111")
					.phoneNumber("123-1234-1234")
					.build()
			);

			// 테스트용 관리자 계정 생성
			User admin = userRepository.save(User.builder()
					.id("admin")
					.password(bcryptPasswordEncoder.encode("123"))
					.name("관리자")
					.studentId("20150000")
					.department(Department.computer)
					.dateOfBirth("960428")
					.phoneNumber("010-1234-5678")
					.lastLoginTime(LocalDateTime.now())
					.roleType(RoleType.ADMIN)
					.build()
			);

			// 테스트용 유저 계정 생성
			User user1 = userRepository.save(User.builder()
					.id("user1")
					.password(bcryptPasswordEncoder.encode("123"))
					.name("유저1")
					.studentId("20150000")
					.department(Department.computer)
					.dateOfBirth("960428")
					.phoneNumber("010-1234-5678")
					.lastLoginTime(LocalDateTime.now())
					.roleType(RoleType.MEMBER)
					.build()
			);

			// 테스트용 유저 계정 생성
			User user2 = userRepository.save(User.builder()
					.id("user2")
					.password(bcryptPasswordEncoder.encode("123"))
					.name("유저2")
					.studentId("20150000")
					.department(Department.software)
					.dateOfBirth("960428")
					.phoneNumber("010-1234-5678")
					.lastLoginTime(LocalDateTime.now())
					.roleType(RoleType.GUEST)
					.build()
			);

			// 공지사항 게시글 생성
			Post notice = postRepository.save(Post.builder()
					.title("SPLUG 공지사항")
					.content("OO월 OO일 OO시 정모입니다.")
					.postType(PostType.notice)
					.user(admin)
					.commentCount(2)
					.build()
			);

			// 자유게시판 게시글 생성
			Post free = postRepository.save(Post.builder()
					.title("반갑습니다.")
					.content("반갑습니다.")
					.postType(PostType.free)
					.user(admin)
					.commentCount(0)
					.build()
			);

			// 활동 게시판 게시글 생성
			Post activity1 = postRepository.save(Post.builder()
					.title("2020 4월 SPLUG 개강 총회")
					.content("개강 총회를 진행하였습니다.")
					.postType(PostType.activity)
					.user(admin)
					.thumbnail("images/no_img.gif")
					.views(0)
					.commentCount(0)
					.build()
			);

			// 활동 게시판 게시글 생성
			Post activity2 = postRepository.save(Post.builder()
					.title("2020 6월 MT")
					.content("MT를 가보았습니다.")
					.postType(PostType.activity)
					.user(admin)
					.thumbnail("images/no_img.gif")
					.views(0)
					.commentCount(0)
					.build()
			);

			// 활동 게시판 게시글 생성
			Post activity3 = postRepository.save(Post.builder()
					.title("2020 10월 축제 주점")
					.content("주점입니다.")
					.postType(PostType.activity)
					.user(admin)
					.thumbnail("images/no_img.gif")
					.views(0)
					.commentCount(0)
					.build()
			);


			// 댓글 생성
			Comment comment1 = commentRepository.save(Comment.builder()
					.post(notice)
					.content("알겠습니다.")
					.user(user1)
					.build()
			);

			// 댓글 생성
			Comment comment2 = commentRepository.save(Comment.builder()
					.post(notice)
					.content("저두요")
					.user(user2)
					.build()
			);

			// 회계 내역 생성
			Account account1 = accountRepository.save(Account.builder()
					.date("2020.03.01")
					.usePlace("SPLUG 2019 이월 회비")
					.useAmount("500000")
					.remainAmount("1000000")
					.build()
			);

			// 회계 내역 생성
			Account account2 = accountRepository.save(Account.builder()
					.date("2020.03.10")
					.usePlace("SPLUG 홍보 포스터 제작")
					.useAmount("-50000")
					.remainAmount("950000")
					.build()
			);
		};
	}
}
