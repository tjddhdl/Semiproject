package com.example.demo.order;

import java.util.List;

import com.example.demo.cart.CartDTO;
import com.example.demo.member.Member;

public interface OrderService {

	default Order dtoToEntity(OrderDTO dto) {
		Member member = new Member();
		member.setMemberNo(dto.getMemberNo());

		Order order = Order.builder().memberNo(member).totalCount(dto.getTotalCount()).totalPrice(dto.getTotalPrice())
				.arrivedDate(dto.getArrivedDate()).status(dto.getStatus()).build();
		return order;
	}

	default OrderDTO entityToDTO(Order o) {
		OrderDTO dto = OrderDTO.builder().orderNo(o.getOrderNo()).memberNo(o.getMemberNo().getMemberNo())
				.totalCount(o.getTotalCount()).totalPrice(o.getTotalPrice()).orderDate(o.getOrderDate())
				.arrivedDate(o.getArrivedDate()).status(o.getStatus()).build();
		return dto;
	}

	// 주문추가
	void orderAdd(List<CartDTO> list);

	// 특정회원 주문목록 조회
	List<OrderDTO> orderSearch(int memberNo);

	// 주문 상세조회
	OrderDTO orderLookUp(int orderNo);

	// 주문 취소(배송전일경우만)
	void orderCancel(int orderNo);
}
