package com.example.demo.member;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	@Test
	void 멤버등록() {
		Member member = Member.builder().id("fdsa").password("fdsa").userName("빵").age(99).role(Role.Customer)
				.address("서울").build();

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
			member.setAge(1);
			repository.save(member);
		}
	}

	@Test
	void 멤버삭제() {
		repository.deleteById(3);
	}
}
