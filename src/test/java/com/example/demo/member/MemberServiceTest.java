package com.example.demo.member;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Role;
import com.example.demo.member.service.MemberService;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService service;

	@Test
	void 멤버가입() {
		MemberDTO dto = MemberDTO.builder().id("bbbb").password("bbbb").userName("bb").age(LocalDate.of(2010, 3, 2))
				.role(Role.Customer).build();

		service.register(dto);
	}

	@Test
	void 회원목록조회() {
		List<MemberDTO> list = service.listLookUp();
		for (MemberDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}

	@Test
	void 회원조회() {
		MemberDTO dto = service.lookUp(4);
		System.out.println("회원: " + dto);
	}

	@Test
	void 회원정보수정() {
		MemberDTO dto = service.lookUp(5);
		dto.setId("aaaa");
		dto.setPassword("asdf");
		service.modify(dto);
		MemberDTO dtoCheck = service.lookUp(5);
		System.out.println("결과: " + dtoCheck);
	}

	@Test
	void 회원정보삭제() {
		service.delete(4);
	}

}
