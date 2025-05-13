package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.CartDTO;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	CartService service;

	@Test
	void 와랄랄() {
		CartDTO dto = CartDTO.builder().memberNo(1).tNo(1).count(3).price(3000).build();
		service.cartAdd(dto);
	}

	@Test
	void 앍앍() {
		CartDTO dto = service.cartLookUp(1);
		System.out.println(dto);
	}

	@Test
	void 목록선택() {
		List<Integer> list = new ArrayList<>();
		list.add(1);

		List<CartDTO> dtos = service.cartSelect(list);
		System.out.println("목록: " + dtos);
	}

	@Test
	void 목록제거() {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(4);

		service.cartClearByIdList(list);
	}
}
