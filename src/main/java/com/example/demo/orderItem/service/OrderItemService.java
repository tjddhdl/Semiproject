package com.example.demo.orderItem.service;

import java.util.List;

import com.example.demo.order.entity.Order;
import com.example.demo.orderItem.dto.OrderItemDTO;
import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.title.entity.Title;

public interface OrderItemService {

	default OrderItem dtoToEntity(OrderItemDTO dto) {
		Order order = new Order();
		order.setOrderNo(dto.getOrderNo());
		Title title = new Title();
		title.setTNo(dto.getTNo());
		OrderItem item = OrderItem.builder().orderNo(order).tNo(title).count(dto.getCount()).price(dto.getPrice())
				.build();
		return item;
	}

	default OrderItemDTO entityToDTO(OrderItem item) {
		OrderItemDTO dto = OrderItemDTO.builder().orderItemNo(item.getOrderItemNo())
				.orderNo(item.getOrderNo().getOrderNo()).tNo(item.getTNo().getTNo()).count(item.getCount())
				.price(item.getPrice()).build();
		return dto;
	}

	// 주문상세 추가
	void orderItemAdd(OrderItemDTO dto);

	// 주문번호로 주문상세목록 조회
	List<OrderItemDTO> orderItemLookUp(int orderNo);

	// 주문상세 삭제(주문번호기반 일괄삭제)
	void orderItemDelete(int orderNo);
}
