package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Title;

@SpringBootTest
public class OrderItemRepositoryTest {

	@Autowired
	OrderItemRepository repository;

	@Autowired
	OrderRepository repository2;

	@Autowired
	TitleRepository repository3;

	@Test
	void 오더아이템추가() {
		Order order = repository2.findById(1).get();
		Title title = repository3.findById(1).get();
		OrderItem item = OrderItem.builder().orderNo(order).tNo(title).count(3).price(title.getPrice() * 3).build();

		System.out.println("아이템: " + item);
		repository.save(item);
	}

	@Test
	void 오더no로검색() {
		Order order = new Order();
		order.setOrderNo(1);
		List<OrderItem> list = repository.findByOrderNo(order);
		for (OrderItem item : list) {
			System.out.println("목록: " + item);
		}
	}

	@Test
	void 오더no로삭제() {
		Order order = new Order();
		order.setOrderNo(1);
		repository.deleteAllByOrderNo(1);

	}
}
