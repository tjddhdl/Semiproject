package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.OrderDTO;

@SpringBootTest
public class OrderServiceTest {

	@Autowired
	OrderService service;

	@Autowired
	CartService cartService;

	@Test
	void 카트먼저추가() {
		CartDTO dto = CartDTO.builder().tNo(2).memberNo(1).price(1000).count(3).build();
		cartService.cartAdd(dto);
	}

	@Test
	void 오더추가() {
		System.out.println("카트정보: " + cartService.cartLookUp(5));
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(6);
		List<CartDTO> dtoList = cartService.cartSelect(list);
		System.out.println("리스트: " + dtoList);
		service.orderAdd(dtoList);
	}

	@Test
	void 오더멤버검색() {
		List<OrderDTO> list = service.orderSearch(1);
		for (OrderDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}

	@Test
	void 오더no검색() {
		OrderDTO dto = service.orderLookUp(3);
		System.out.println("3번오더: " + dto);
	}

	@Test
	void 오더취소() {
		service.orderCancel(1);
	}
}
