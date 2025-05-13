package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;
import com.example.demo.entity.enumfolder.Role;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	@Test
	void 멤버등록() {
		Member member = Member.builder().id("asdf").password("asdf").userName("밥").age(10).role(Role.Customer)
				.address("인천").build();

		repository.save(member);
	}

}
