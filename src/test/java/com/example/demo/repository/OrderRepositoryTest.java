package com.example.demo.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;
import com.example.demo.entity.Order;
import com.example.demo.entity.enumfolder.Status;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	OrderRepository repository;

	@Autowired
	MemberRepository repository2;

	@Test
	void 오더등록() {
		Member member = repository2.findById(1).get();
		System.out.println(member);
		LocalDateTime dateTime = LocalDateTime.now();
		Order order = Order.builder().memberNo(member).totalCount(3).totalPrice(3).orderDate(dateTime)
				.status(Status.Before).build();
		repository.save(order);

	}
}
