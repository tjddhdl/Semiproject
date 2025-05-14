package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.OrderItemDTO;

@SpringBootTest
public class OrderItemServiceTest {

	@Autowired
	OrderItemService service;

	@Test
	void 오더아이템추가() {
		OrderItemDTO dto = OrderItemDTO.builder().orderNo(1).tNo(2).count(3).price(1000).build();
		service.orderItemAdd(dto);
	}

	@Test
	void 오더아이템목록조회() {
		List<OrderItemDTO> list = service.orderItemLookUp(1);
		for (OrderItemDTO dto : list) {
			System.out.println("주문번호별 목록: " + dto);
		}
	}
}
