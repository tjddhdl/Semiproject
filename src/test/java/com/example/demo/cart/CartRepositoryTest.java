package com.example.demo.cart;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.cart.Cart;
import com.example.demo.cart.CartRepository;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.title.Title;
import com.example.demo.title.TitleRepository;

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

	@Test
	void 카트조회() {
		Optional<Cart> optional = repository.findById(5);
		if (optional.isPresent()) {
			Cart cart = optional.get();
			System.out.println(cart);
		}
	}

	@Test
	void 카트수정() {
		Optional<Cart> optional = repository.findById(5);
		if (optional.isPresent()) {
			Cart cart = optional.get();

			Title title = new Title();
			title.setTNo(1);
			cart.setTNo(title);
			repository.save(cart);
		}
	}

	@Test
	void 카트삭제() {
		repository.deleteById(7);
	}

}
