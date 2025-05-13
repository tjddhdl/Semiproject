package com.example.demo.service;

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
}
