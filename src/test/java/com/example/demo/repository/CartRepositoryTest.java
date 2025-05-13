package com.example.demo.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.Title;

@SpringBootTest
public class CartRepositoryTest {

	@Autowired
	CartRepository repository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	TitleRepository titleRepository;

	@Test
	void 카트추가() {
		Optional<Member> member = memberRepository.findById(1);
		Optional<Title> title = titleRepository.findById(1);

		Cart cart = Cart.builder().memberNo(member.get()).tNo(title.get()).count(3).price(3000).build();

		repository.save(cart);
	}
}
