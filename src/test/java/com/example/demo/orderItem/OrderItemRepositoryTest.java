package com.example.demo.orderItem;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.order.Order;
import com.example.demo.order.OrderRepository;
import com.example.demo.orderItem.OrderItem;
import com.example.demo.orderItem.OrderItemRepository;
import com.example.demo.title.Title;
import com.example.demo.title.TitleRepository;

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
		Order order = repository2.findById(3).get();
		Title title = repository3.findById(1).get();
		OrderItem item = OrderItem.builder().orderNo(order).tNo(title).count(3).price(title.getPrice() * 3).build();

		System.out.println("아이템: " + item);
		repository.save(item);
	}

	@Test
	void 오더no로검색() {
		Order order = new Order();
		order.setOrderNo(3);
		List<OrderItem> list = repository.findByOrderNo(order);
		for (OrderItem item : list) {
			System.out.println("목록: " + item);
		}
	}

	@Test
	void 오더정보수정() {
		Optional<OrderItem> optional = repository.findById(8);
		if (optional.isPresent()) {
			OrderItem item = optional.get();
			item.setCount(161);
			item.setPrice(97979797);
			Title title = new Title();
			title.setTNo(4);
			item.setTNo(title);
			repository.save(item);
		}

	}

	@Test
	void 오더no로삭제() {
		Order order = new Order();
		order.setOrderNo(8);
		repository.deleteAllByOrderNo(1);

	}

	@Test
	void 오더아이템삭제() {
		repository.deleteById(8);
	}
}
