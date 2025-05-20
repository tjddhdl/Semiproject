package com.example.demo.cart;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	CartService service;

	@Test
	void 상품추가() {
		CartDTO dto = CartDTO.builder().memberNo(1).tNo(4).count(3).price(300000).build();
		service.cartAdd(dto);
	}

	@Test
	void 장바구니조회() {
		CartDTO dto = service.cartLookUp(8);
		System.out.println(dto);
	}

	@Test
	void 장바구니목록조회() {
		List<CartDTO> list = service.listLookUp(1);
		for (CartDTO dto : list) {
			System.out.println("목록: " + dto);
		}
	}

	@Test
	void 장바구니목록선택() {
		List<Integer> list = new ArrayList<>();
		list.add(8);

		List<CartDTO> dtos = service.cartSelect(list);
		System.out.println("목록: " + dtos);
	}

	@Test
	void 장바구니목록제거() {
		List<Integer> list = new ArrayList<>();
		list.add(8);

		List<CartDTO> dtos = service.cartSelect(list);
		System.out.println(dtos);
		service.cartClearByDTOList(dtos);
	}

	@Test
	void 장바구니수량수정() {
		CartDTO dto = service.cartLookUp(13);
		System.out.println(dto);
		dto.setCount(1);
		service.modify(dto);
	}
}
