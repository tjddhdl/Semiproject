package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	OrderItemRepository repository;
	
	// 주문상세 추가
	@Override
	public void orderItemAdd(OrderItemDTO dto) {
		OrderItem item = dtoToEntity(dto);
		repository.save(item);
	}

	// 주문상세 삭제(주문번호기반 일괄삭제)
	@Override
	public void orderItemDelete(int orderNo) {
		
		
	}

}
