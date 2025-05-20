package com.example.demo.order;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.service.OrderService;

@SpringBootTest
public class OrderServiceTest {

	@Autowired
	OrderService service;

	@Autowired
	CartService cartService;

	@Test
	void 카트먼저추가() {
		CartDTO dto = CartDTO.builder().tNo(4).memberNo(1).price(1000).count(16).build();
		cartService.cartAdd(dto);
	}

	@Test
	void 오더추가() {
		System.out.println("카트정보: " + cartService.cartLookUp(11));
		List<Integer> list = new ArrayList<>();
		list.add(11);
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
		OrderDTO dto = service.orderLookUp(5);
		System.out.println("5번오더: " + dto);
	}

	@Test
	void 오더수정() {
		OrderDTO dto = service.orderLookUp(6);
		dto.setTotalPrice(6000);
		service.orderModify(dto);
	}

	@Test
	void 오더취소() {
		service.orderCancel(8);
	}
}
