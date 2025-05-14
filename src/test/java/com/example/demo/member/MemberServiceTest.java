package com.example.demo.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.MemberDTO;
import com.example.demo.member.MemberService;
import com.example.demo.member.Role;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService service;

	@Test
	void 멤버가입() {
		MemberDTO dto = MemberDTO.builder().id("asdfzxcv").password("qwerasdf").userName("땃쥐").age(31)
				.role(Role.Customer).build();

		service.register(dto);
	}

	@Test
	void 회원조회() {
		MemberDTO dto = service.lookUp(4);
		System.out.println("회원: " + dto);
	}

	@Test
	void 회원정보수정() {
		MemberDTO dto = service.lookUp(4);
		dto.setAge(28);
		dto.setUserName("거븍븍");
		service.modify(dto);
	}

	@Test
	void 회원정보삭제() {
		service.delete(4);
	}

}
