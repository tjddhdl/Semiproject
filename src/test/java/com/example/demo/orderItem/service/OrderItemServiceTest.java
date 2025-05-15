package com.example.demo.orderItem.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.orderItem.dto.OrderItemDTO;
import com.example.demo.orderItem.service.OrderItemService;

@SpringBootTest
public class OrderItemServiceTest {

	@Autowired
	OrderItemService service;

	@Test
	void 오더아이템추가() {
		OrderItemDTO dto = OrderItemDTO.builder().orderNo(6).tNo(4).count(3).price(1000).build();
		service.orderItemAdd(dto);
	}

	@Test
	void 오더아이템목록조회() {
		List<OrderItemDTO> list = service.orderItemLookUp(6);
		for (OrderItemDTO dto : list) {
			System.out.println("주문번호별 목록: " + dto);
		}
	}

	@Test
	void 오더no로삭제() {
		service.orderItemDelete(6);
	}
}
