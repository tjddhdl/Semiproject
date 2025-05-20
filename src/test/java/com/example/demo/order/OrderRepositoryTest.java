package com.example.demo.order;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.Status;
import com.example.demo.order.repository.OrderRepository;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;

	@Autowired
	MemberRepository repository2;

	@Test
	void 오더등록() {
		Optional<Member> member = repository2.findById(1);
		System.out.println(member.get());
		Order order = Order.builder().memberNo(member.get()).totalCount(3).totalPrice(3).status(Status.Before).build();
		repository.save(order);

	}

	@Test
	void 오더목록조회() {
		List<Order> list = repository.findAllByMemberNo(1);
		for (Order order : list) {
			System.out.println(order);
		}
	}

	@Test
	void 오더수정() {
		Optional<Order> optional = repository.findById(4);
		if (optional.isPresent()) {
			Order order = optional.get();
			order.setStatus(Status.Progress);
			repository.save(order);
		}
	}

	@Test
	void 오더삭제() {
		repository.deleteById(5);
	}
}
