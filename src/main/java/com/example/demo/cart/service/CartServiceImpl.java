package com.example.demo.cart.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.cart.dto.CartDTO;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.repository.CartRepository;
import com.example.demo.title.dto.TitleDTO;
import com.example.demo.title.entity.Title;
import com.example.demo.title.repository.TitleRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	TitleRepository titleRepository;

	// 장바구니에 상품 추가
	@Override
	public void cartAdd(CartDTO dto) {
		Optional<Title> optional = titleRepository.findById(dto.getTNo());
		Title title = optional.get();

		// 동일 사용자가 동일 상품을 추가할 시 기존 장바구니에 수량 추가
		// 그렇지 않으면 새 장바구니로 추가
		Optional<Cart> optional2 = cartRepository.findOneByMemberNoAndTNo(dto.getMemberNo(), dto.getTNo());
		if (optional2.isPresent()) {
			Cart cart = optional2.get();
			cart.setCount(cart.getCount() + dto.getCount());

			// 상품 재고 확인
			if (title.getStock() < cart.getCount()) {
				throw new IllegalStateException("상품 재고 부족: " + title.getTitleName());
			}
			cartRepository.save(cart);
		} else {

			// 상품 재고 확인
			if (title.getStock() < dto.getCount()) {
				throw new IllegalStateException("상품 재고 부족: " + title.getTitleName());
			}
			Cart cart = dtoToEntity(dto);
			cartRepository.save(cart);
		}

	}

	// 장바구니 조회
	@Override
	public CartDTO cartLookUp(int no) {
		Optional<Cart> optional = cartRepository.findById(no);
		if (optional.isPresent()) {
			CartDTO dto = entityToDTO(optional.get());
			return dto;
		}
		return null;
	}

	// 장바구니에서 특정 목록 선택
	@Override
	public List<CartDTO> cartSelect(List<Integer> cartNo) {
		List<Cart> list = cartRepository.findAllById(cartNo);
		List<CartDTO> dtoList = list.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
		return dtoList;
	}

	// 장바구니에서 특정 상품 제거
	@Override
	public void cartClearByDTOList(List<CartDTO> list) {
		List<Cart> cartList = list.stream().map(dto -> dtoToEntityDelete(dto)).collect(Collectors.toList());
		cartRepository.deleteAll(cartList);
	}

}
