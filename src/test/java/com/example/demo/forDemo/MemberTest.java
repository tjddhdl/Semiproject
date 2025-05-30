package com.example.demo.forDemo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.Role;
import com.example.demo.member.service.MemberService;

@SpringBootApplication
public class MemberTest {

	@Autowired
	MemberService memberService;

	// 1. 관리자 기능 테스트용 아이디 2개 만들기
	@Test
	void 아이디생성() {
		MemberDTO dto1 = MemberDTO.builder().id("admin").password("admin").userName("관리자").age(LocalDate.of(1999, 1, 1))
				.role(Role.Admin).build();
		MemberDTO dto2 = MemberDTO.builder().id("customer").password("customer").userName("회원")
				.age(LocalDate.of(1998, 1, 1)).role(Role.Customer).build();
		memberService.register(dto1);
		memberService.register(dto2);
	}
	
	

}
