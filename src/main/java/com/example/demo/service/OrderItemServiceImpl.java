package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository repository;

	// 주문상세 추가
	@Override
	public void orderItemAdd(OrderItemDTO dto) {
		OrderItem item = dtoToEntity(dto);
		repository.save(item);
	}

	// 주문번호로 주문상세목록 조회
	@Override
	public List<OrderItemDTO> orderItemLookUp(int orderNo) {
		Order order = new Order();
		order.setOrderNo(orderNo);
		List<OrderItem> list = repository.findByOrderNo(order);
		List<OrderItemDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 주문상세 삭제(주문번호기반 일괄삭제)
	@Override
	public void orderItemDelete(int orderNo) {
		repository.deleteAllByOrderNo(orderNo);
	}

}
