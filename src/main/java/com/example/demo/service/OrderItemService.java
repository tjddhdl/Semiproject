package com.example.demo.service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Title;

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

	// 주문상세 삭제(주문번호기반 일괄삭제)
	void orderItemDelete(int orderNo);
}
