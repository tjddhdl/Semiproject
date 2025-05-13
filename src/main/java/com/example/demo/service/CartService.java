package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CartDTO;
import com.example.demo.entity.Cart;

public interface CartService {

	default Cart dtoToEntity(CartDTO dto) {
		Cart cart = Cart.builder().memberNo(dto.getMemberNo()).tNo(dto.getTNo()).count(dto.getCount())
				.price(dto.getPrice()).build();
		return cart;
	}

	default CartDTO entityToDTO(Cart cart) {
		CartDTO dto = CartDTO.builder().cartNo(cart.getCartNo()).memberNo(cart.getMemberNo()).tNo(cart.getTNo())
				.cartNo(cart.getCartNo()).price(cart.getCartNo()).build();
		return dto;
	}

	// 장바구니에 상품 추가
	void cartAdd(int memberNo, int tNo, int count, int price);

	// 장바구니 조회
	CartDTO cartLookUp(int no);

	// 장바구니에서 특정 목록 선택
	List<CartDTO> cartSelect(List<Integer> cartNo);

	// 장바구니에서 특정 상품 제거
	void cartClearByIdList(List<Integer> cartNo);
}
