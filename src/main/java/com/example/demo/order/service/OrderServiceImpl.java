package com.example.demo.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.service.CartService;
import com.example.demo.member.entity.Member;
import com.example.demo.order.dto.OrderDTO;
import com.example.demo.order.entity.Order;
import com.example.demo.order.entity.Status;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.orderItem.dto.OrderItemDTO;
import com.example.demo.orderItem.service.OrderItemService;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Title;
import com.example.demo.title.repository.TitleRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository repository;

	@Autowired
	TitleRepository titleRepository;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	CartService cartService;

	// 주문추가
	@Override
	@Transactional
	public void orderAdd(List<CartDTO> list) {

		// 재고 수량 확인
		for (CartDTO dto : list) {
			Optional<Title> optional = titleRepository.findById(dto.getTNo());
			Title title = optional.get();
			if (dto.getCount() > title.getStock()) {
				throw new IllegalStateException("상품 재고 부족: " + title.getStock());
			}
		}

		int totalCount = 0;
		int totalPrice = 0;

		// 재고 차감 / totalCount, totalPrice 계산
		for (CartDTO dto : list) {
			Title title = titleRepository.findById(dto.getTNo()).get();
			title.setStock(title.getStock() - dto.getCount());
			titleRepository.save(title);
			totalCount += dto.getCount();
			totalPrice += dto.getCount() * dto.getPrice();
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
		List<Order> list = repository.findAllByMemberNo(memberNo);
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

	// 주문 수정
	@Override
	public void orderModify(OrderDTO dto) {
		Optional<Order> optional = repository.findById(dto.getOrderNo());
		if (optional.isPresent()) {
			Order order = optional.get();
			if (dto.getMemberNo() != null) {
				Member member = new Member();
				member.setMemberNo(dto.getMemberNo());
				order.setMemberNo(member);
			}
			if (dto.getTotalCount() != null)
				order.setTotalCount(dto.getTotalCount());
			if (dto.getTotalPrice() != null)
				order.setTotalPrice(dto.getTotalPrice());
			if (dto.getArrivedDate() != null)
				order.setArrivedDate(dto.getArrivedDate());
			if (dto.getStatus() != null)
				order.setStatus(dto.getStatus());
			repository.save(order);

		}

	}

	// 주문 취소(배송전일경우만)
	@Override
	public void orderCancel(int orderNo) {
		Optional<Order> optional = repository.findById(orderNo);
		Order order = optional.get();
		if (order.getStatus() == Status.Before) {

			// 재고 복원
			List<OrderItemDTO> list = orderItemService.orderItemLookUp(orderNo);
			for (OrderItemDTO dto : list) {
				Title title = titleRepository.findById(dto.getTNo()).get();
				title.setStock(title.getStock() + dto.getCount());
				titleRepository.save(title);
			}

			orderItemService.orderItemDelete(orderNo);
			repository.deleteById(orderNo);
		}
	}

}
