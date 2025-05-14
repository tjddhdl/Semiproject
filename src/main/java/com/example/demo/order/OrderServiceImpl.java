package com.example.demo.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cart.CartDTO;
import com.example.demo.cart.CartService;
import com.example.demo.orderItem.OrderItemDTO;
import com.example.demo.orderItem.OrderItemService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	CartService cartService;

	// 주문추가
	@Override
	@Transactional
	public void orderAdd(List<CartDTO> list) {

		// totalCount, totalPrice 계산
		int totalCount = 0;
		int totalPrice = 0;
		for (CartDTO dtoForCount : list) {
			totalCount += dtoForCount.getCount();
			totalPrice += dtoForCount.getCount() * dtoForCount.getPrice();
		}

		// order 저장
		CartDTO dtoindex0 = list.get(0);
		OrderDTO orderDto = OrderDTO.builder().memberNo(dtoindex0.getMemberNo()).totalCount(totalCount)
				.totalPrice(totalPrice).status(Status.Before).build();
		Order order = dtoToEntity(orderDto);
		repository.save(order);

		// orderItem 리스트 저장
		for (CartDTO dto : list) {
			OrderItemDTO itemDTO = OrderItemDTO.builder().orderNo(order.getOrderNo()).tNo(dto.getTNo())
					.count(dto.getCount()).price(dto.getPrice()).build();
			orderItemService.orderItemAdd(itemDTO);
		}

		// 카트 비우기
		cartService.cartClearByDTOList(list);
	}

	// 특정회원 주문목록 조회
	@Override
	public List<OrderDTO> orderSearch(int memberNo) {
		List<Order> list = repository.findAllByMemberNo(1);
		List<OrderDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 주문 상세조회
	@Override
	public OrderDTO orderLookUp(int orderNo) {
		Optional<Order> optional = repository.findById(orderNo);
		if (optional.isPresent()) {
			OrderDTO dto = entityToDTO(optional.get());
			return dto;
		}
		return null;
	}

	// 주문 취소(배송전일경우만)
	@Override
	public void orderCancel(int orderNo) {
		Optional<Order> optional = repository.findById(orderNo);
		Order order = optional.get();
		if (order.getStatus() == Status.Before) {
			orderItemService.orderItemDelete(orderNo);
			repository.deleteById(orderNo);
		}
	}

}
