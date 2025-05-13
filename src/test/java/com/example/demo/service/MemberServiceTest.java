package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.enumfolder.Role;

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
		MemberDTO dto = service.lookUp(2);
		System.out.println("회원: " + dto);
	}

	@Test
	void 회원정보수정() {
		MemberDTO dto = service.lookUp(2);
		dto.setAge(28);
		dto.setUserName("거븍븍");
		service.modify(dto);
	}
	
	@Test
	void 회원정보삭쩨() {
		service.delete(2);
	}

}
