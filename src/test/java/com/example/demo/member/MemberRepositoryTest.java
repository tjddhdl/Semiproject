package com.example.demo.member;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.Role;
import com.example.demo.member.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	@Test
	void 멤버등록() {
		Member member = Member.builder().id("fdsa").password("fdsa").userName("빵").age(LocalDate.of(1989, 12, 1))
				.role(Role.Customer).address("서울").build();

		repository.save(member);
	}

	@Test
	void 멤버조회() {
		Optional<Member> optional = repository.findById(3);
		if (optional.isPresent()) {
			System.out.println(optional.get());
		}
	}

	@Test
	void 멤버수정() {
		Optional<Member> optional = repository.findById(3);
		if (optional.isPresent()) {
			Member member = optional.get();
			member.setAddress("경기도");
			member.setAge(LocalDate.of(1999, 1, 12));
			repository.save(member);
		}
	}

	@Test
	void 멤버삭제() {
		repository.deleteById(3);
	}

	@Test
	void 멤버아이디중복확인() {
		Member member = repository.findByMemberId("asdf21231");
		System.out.println("멤버검색: " + member);
	}
}
